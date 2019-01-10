/**
 * 
 */
app.controller('NotificationCtrl',function($scope,$rootScope,$location,NotificationService,$routeParams){
	function getAllNotificationsNotViewed(){
		NotificationService.getAllNotificationsNotViewed().then(function(response){
			$rootScope.notifications=response.data
			$rootScope.notificationCount=$rootScope.notifications.length
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	if($routeParams.notificationId!=undefined){
		NotificationService.getNotification($routeParams.notificationId).then(
				function(response){
					$scope.notification=response.data
				},function(response){
					if(response.status==401)
						$location.path('/login')
				})
		NotificationService.updateNotification($routeParams.notificationId).then(
				function(response){
					getAllNotificationsNotViewed()
				},
				function(response){
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	getAllNotificationsNotViewed()
})


