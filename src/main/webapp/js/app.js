angular.module('MonApp', ['toaster','ngAnimate']) 
.controller('mainController', function($scope,$http,toaster) {
 
        function rafraichirLeTableau(){
            $http.get('rest/eleves/totalEleves').then(function(data){
                $scope.eleves = data.data;
            })
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
 
         rafraichirLeTableau();
/* FIN DU CONTROLEUR */
});