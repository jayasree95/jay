/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/registerUser',{controller:'UserCtrl',templateUrl:'views/registrationform.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
	.when('/getuser',{controller:'UserCtrl',templateUrl:'views/updateform.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
	.when('/alljobs',{controller:'JobCtrl',templateUrl:'views/jobslist.html'})
	.when('/addblog',{controller:'BlogCtrl',templateUrl:'views/blogform.html'})
	.when('/blogswaitingforapproval/:value',{controller:'BlogCtrl',templateUrl:'views/blogswaitingforapproval.html'})
	.when('/getblogwaitingforapproval/:blogId',{controller:'BlogInDetailCtrl',templateUrl:'views/blogapprovalform.html'})
	.when('/getblogapproved/:blogId',{controller:'BlogInDetailCtrl',templateUrl:'views/blogdetails.html'})
	.when('/blogsapproved/:value',{controller:'BlogCtrl',templateUrl:'views/blogsapproved.html'})
	.when('/updateblogpost/:blogId',{controller:'BlogInDetailCtrl',templateUrl:'views/updateblogform.html'})
	.when('/home',{controller:'NotificationCtrl',templateUrl:'views/home.html'})
	.when('/getnotification/:notificationId',{controller:'NotificationCtrl',templateUrl:'views/notificationdetails.html'})
	.when('/uploadprofilepic',{templateUrl:'views/profilepicture.html'})
	.when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggesteduserslist.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/pendingrequests.html'})
    .when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendslist.html'})
    .when('/chat',{controller:'ChatCtrl',templateUrl:'views/chat.html'})
	.otherwise({controller:'UserCtrl',templateUrl:'views/home.html'})
})

//ngRoute -> $routeProvider and ng-view
//ngCookies -> $cookieStore

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('user')
		
    $rootScope.logout=function(){
		UserService.logout().then(function(response){
			delete $rootScope.user
			$cookieStore.remove('user')
			$location.path('/login')
		},function(response){
			if(response.status==401){//session attribute email is not there in HttpSession
			delete $rootScope.user
			$cookieStore.remove('user')
			$location.path('/login')
			}
		})
	}
})