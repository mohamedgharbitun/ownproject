angular
.module('MonApp', ['toaster','ngAnimate','ngRoute','ui.router'])
.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
	
	$locationProvider.html5Mode(false);
	//$urlRouterProvider.otherwise('');
	$locationProvider.hashPrefix('');
	
    $stateProvider
    .state('main', {
        url: '/',
        templateUrl: './index.html',
        controller: 'mainController'
    })
    .state('test', {
        url: '/test',
        templateUrl: './views/test.html',
        controller: 'testController'
    })
})
.controller('mainController', function($scope,$http,toaster,$state,$location) {
	console.log("passage par maincontroller");
 
        function rafraichirLeTableau(){
        	$http({
                method : 'GET',
                url : 'rest/eleves/totalEleves'
              }).then(function(response) {
                $scope.eleves = JSON.stringify(response.data);
              });
        };
 
        $scope.editer = function(eleve){
            $http.get('rest/eleves/getEleveParId/'+eleve.id)
            .then(function(data){
                $scope.eleve = data.data;
            })
            .catch(function(data) {
            	toaster.pop('error', "error", "Aucun eleve trouvé!");
            })
         };
 
        $scope.validerEdition = function(){
            $http.post('rest/eleves/validerEdition/',$scope.eleve)
            .then(function(data){
            	toaster.pop('success', "Succès", "Modification effectuée");
                rafraichirLeTableau();
            })
            .catch(function(data) {
            	toaster.pop('error', "error", "Aucun eleve trouvé!");
            })
         };
 
        $scope.ajouter = function(){
            $http.post('rest/eleves/ajouterEleve/',$scope.nouvelEleve).then(function(data){
                toaster.pop('success', "Succès", "Ajout effectué");
                rafraichirLeTableau();
            })
         };
         
         $scope.supprimer = function(eleve){
            $http.get('rest/eleves/supprimerEleveParId/'+ eleve.id).then(function(data){
            	toaster.pop('info', "Succès", "Suppression effectuée");
                rafraichirLeTableau();
            })
         };
         
         $scope.redirectTest = function() {
        	 $state.go('test');
		};
 
         rafraichirLeTableau();
/* FIN DU CONTROLEUR */
});