/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8089/middleware"
	
	userService.registerUser=function(user){//user <- usercontroller  <-registrationform.html
	 return	$http.post(BASE_URL+ "/register",user)
	}
	
	userService.login=function(user){
		return $http.put(BASE_URL+"/login",user)
	}
	
	userService.logout=function(){
		return $http.put(BASE_URL + "/logout")
	}

	userService.getUser=function(){
		return $http.get(BASE_URL + "/getuser")
	}
	
	userService.update=function(user){
		return $http.put(BASE_URL + "/updateprofile",user)
	}
	return userService;
})
