/**
 * 
 */
app.controller('FriendCtrl',function($scope,$location,FriendService){
	function getAllSuggestedUsers(){
	FriendService.getAllSuggestedUsers().then(function(response){
		$scope.suggestedUsers=response.data //List<User> in JSON representation
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	$scope.sendFriendRequest=function(toId){//toId is user object , it is the value for toId property in Friend entity
		FriendService.sendFriendRequest(toId).then(
				function(response){
					//S in (A - (B U C))
					getAllSuggestedUsers()
				},function(response){
					if(response.status==401)
						$location.path('/login')
				})
	}
	
	//Get the list of pending requests
	function getAllPendingRequests(){
		FriendService.getAllPendingRequests().then(
				function(response){
					$scope.pendingRequests=response.data
				},
				function(response){
					if(response.status==401)
						$location.path('/login')
				})
	}
	
    $scope.acceptFriendRequest=function(request){//request is a Friend Object
    	FriendService.acceptFriendRequest(request).then(
    			function(response){
    				getAllPendingRequests()
    			},function(response){
    				if(response.status==401)
						$location.path('/login')
    			})
    }	
	
    $scope.deleteFriendRequest=function(request){
    	FriendService.deleteFriendRequest(request).then(
    	function(response){
			getAllPendingRequests()
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
    }
	
    function getAllFriends(){
    	FriendService.getAllFriends().then(
    			function(response){
    				//response.data is Array of User object
    				$scope.friends=response.data
    			},function(response){
    				if(response.status==401)
    					$location.path('/login')
    			})
    }
    $scope.getMutualFriends=function(useremail){
    	FriendService.getMutualFriends(useremail).then(function(response){
    		$scope.mutualfriends=response.data
    		$scope.forUser=useremail
    	},function(response){
    		if(response.status==401)
				$location.path('/login')
    	})
    }
    
	getAllSuggestedUsers()
	getAllPendingRequests()
	getAllFriends()
	
})