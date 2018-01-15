angular
.module('MonApp', ['toaster','ngAnimate','ui.router'])
.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
	
	$locationProvider.html5Mode(false);
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
 
        function rafraichirLeTableau(){
     	
        	$http.get("rest/eleves/totalEleves")
            .then(
	            function (response) {
	            	 $scope.eleves = response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching eleves');
	            })
        };
 
        $scope.editer = function(eleve){
            $http.get('rest/eleves/getEleveParId/'+eleve.id)
            .then(
        		function(response){
                	$scope.eleve = response.data;
            	},
            	function(errResponse) {
            		toaster.pop('error', "error", "Aucun eleve trouvé!");
            	})
         };
 
        $scope.validerEdition = function(){
            $http.post('rest/eleves/validerEdition/',$scope.eleve)
            .then(
        		function(response){
	            	toaster.pop('success', "Succès", "Modification effectuée");
	                rafraichirLeTableau();
            	},
            	function(errResponse) {
            		toaster.pop('error', "error", "erreur de validation!");
            	})
         };
 
        $scope.ajouter = function(){
            $http.post('rest/eleves/ajouterEleve/',$scope.nouvelEleve)
            .then(
            	function(response){
	                toaster.pop('success', "Succès", "Ajout effectué ");
	                rafraichirLeTableau();
            	},
	        	function(errResponse) {
	        		toaster.pop('error', "error", "erreur lors de l'ajout d'un eleve!");
            	})
         };
         
         $scope.supprimer = function(eleve){
            $http.delete('rest/eleves/supprimerEleveParId/'+ eleve.id)
            .then(
	            function(response){
	            	toaster.pop('info', "Succès", "Suppression effectuée");
	                rafraichirLeTableau();
	            },
	        	function(errResponse) {
	        		toaster.pop('error', "error", "erreur de suppression de l'eleve!");
	        	})
         };
         
         $scope.redirectTest = function() {
        	 $state.go('test');
		};
 
         rafraichirLeTableau();
/* FIN DU CONTROLEUR */
});