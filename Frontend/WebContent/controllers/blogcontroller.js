/**
 * 
 */
app.controller('BlogCtrl',function($scope,BlogService,$location,$rootScope,$routeParams){
	$scope.addBlog=function(blog){
		BlogService.addBlog(blog).then(function(response){
			alert('blogpost is added successfully and it is waiting for approval...')
			$scope.blog={}
		},function(response){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	//$scope.blogsWaitingForApproval  -- only for ADMIN
	//$scope.blogsApproved  - all roles
	
	function blogsWaitingForApproval(){ //gets executed only for admin
		alert('BlogsWaitingForApproval')
		//select * from blogpost where approved=0 -response.data -> 
		BlogService.blogsWaitingForApproval().then(function(response){
			$scope.blogs=response.data
		},function(response){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function blogsApproved(){
		alert('BlogsApproved')
		BlogService.blogsApproved().then(function(response){
			//select * from blogpost where approved=1
			$scope.blogsApproved=response.data
		},function(response){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	if($rootScope.user.role=='ADMIN' && $routeParams.value==0)
	blogsWaitingForApproval()//call the function only if logged in user role is 'ADMIN'
	
	if($routeParams.value==1)
	blogsApproved()
	
	
})