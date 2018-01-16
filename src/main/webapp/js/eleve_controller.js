'use strict';
 
angular.module('MonApp').controller('EleveController', ['$scope', 'EleveService','toaster', function($scope, EleveService, toaster) {
 
	$scope.eleve = {id:null,prenom:'',nom:''};
	$scope.eleves={id:null,prenom:'',nom:''};
	$scope.eleves=[];
    
    totalEleves();
 
    function totalEleves(){
    	EleveService.totalEleves()
            .then(
            function(d) {
            	$scope.eleves = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    $scope.editer = function(eleve){
	    	EleveService.getEleveParId(eleve)
	        .then(
	        function(d) {
	        	$scope.eleve = d;
	        },
	        function(errResponse){
	        	toaster.pop('error', "error", "Aucun eleve trouvé!");
	        }
	    );
    };
    
    $scope.validerEdition = function(){
    	EleveService.validerEdition($scope.eleve)
		        .then(
		        function(d) {
		        	toaster.pop('success', "Succès", "Modification effectuée");
		        	$scope.eleve = d;
		        	totalEleves();
		        },
		        function(errResponse){
		        	toaster.pop('warning', "Erreur", "Eleve non trouvé !");
		        }
	        );
    };
    
	  $scope.ajouter = function(){
		EleveService.ajouterEleve($scope.nouvelEleve)
	        .then(
	        function(d) {
	        	toaster.pop('success', "Succès", "Ajout effectué ");
	        	reset();
	        	totalEleves();
	        },
	        function(errResponse){
	        	toaster.pop('error', "error", "erreur lors de l'ajout d'un eleve!");
	        }
	        );
	  };
	  
	  $scope.supprimer = function(eleve){
		  EleveService.supprimerEleveParId(eleve)
	        .then(
	        function(d) {
	        	toaster.pop('info', "Succès", "Suppression effectuée");
	        	reset();
	        	totalEleves();
	        },
	        function(errResponse){
	        	toaster.pop('error', "error", "erreur de suppression de l'eleve!");
	        }
	        );
     };
  
    function reset(){
        $scope.nouvelEleve = {};
        $scope.eleve = {};
        $scope.formCreate.$setPristine(); //reset Form
        $scope.formModif.$setPristine(); 
    }
}]);