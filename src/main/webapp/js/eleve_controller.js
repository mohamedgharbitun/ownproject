'use strict';
 
angular.module('MonApp').controller('EleveController', ['$scope', 'EleveService','toaster', function($scope, EleveService, toaster) {
 
	$scope.eleve = {id:null,prenom:'',nom:'', age: ''};
	$scope.eleves={id:null,prenom:'',nom:'', age: ''};
	$scope.eleves=[];
    
    totalEleves();
 
    function totalEleves(){
    	EleveService.totalEleves()
            .then(
            function(d) {
            	$scope.eleves = d;
            	$scope.gridOptions.data = $scope.eleves;

            },
            function(errResponse){
                console.error('Error while fetching Users');
                toaster.pop('error', "error", "Erreur technique lors de recherche des élèves!");
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
	        	toaster.pop('error', "error", "Aucun élève trouvé!");
	        }
	    );
    };
    
    $scope.validerEdition = function(){
	if($scope.eleve.prenom === "" && $scope.eleve.nom === "")
		{
    		toaster.pop('warning', "Warning", "Veuillez renseigner un nom ou un prénom!");
    		return ;
		}
    	
    	EleveService.validerEdition($scope.eleve)
		        .then(
		        function(d) {
		        	toaster.pop('success', "Succès", "Modification effectuée");
		        	$scope.eleve = d;
		        	totalEleves();
		        },
		        function(errResponse){
		        	toaster.pop('error', "Erreur", "Elève non trouvé !");
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
     
     var deletionCellTemplate =  '<button class="glyphicon glyphicon-remove-circle" style="color:red;height: 27px;" title="Supprimer un élève" ng-click="grid.appScope.supprimer(row.entity)"></button>'+
     							 '<button class="glyphicon glyphicon-edit" style="color:#507ab2;height: 27px;" title="Modifier un élève" ng-click="grid.appScope.editer(row.entity)"></button>';
     
     $scope.gridOptions = {
    		 enableCellSelection: false,
    		 enableColumnMenus: false,
    		 enableRowHeaderSelection: false,
    		 enableCellEdit : false,
    		 enableCellEditOnFocus: true,
    		 enableSorting: false,
    		 enableFiltering : false,
    		 columnDefs : 
    			 [
    				 { field:'delete',displayName :'',width: 73, cellTemplate: deletionCellTemplate , resizable: false},
    	    		 { field: 'id',name :'id', displayName: 'Identifiant', width: 100, resizable: false},
    	             { field: 'prenom',name:'prenom', displayName: 'Prénom', width: 130 , resizable: false},
    	             { field: 'nom',name : 'nom', displayName : 'Nom', width: 130, resizable: false},
    	             { field: 'age',name :'age', displayName : 'Age', resizable: false}
    	             
    	         ]
     };
    		 
  
    function reset(){
        $scope.nouvelEleve = {};
        $scope.eleve = {};
        $scope.formCreate.$setPristine(); //reset Form
        $scope.formModif.$setPristine(); 
    }
}]);