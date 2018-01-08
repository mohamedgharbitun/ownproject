angular.module('MonApp', [])
 
.controller('Controleur1', function($scope,$http) {
 
        function rafraichirLeTableau(){
            $http.get('rest/eleves/totalEleves').then(function(data){
                $scope.eleves = data.data;
 
            })
        };
 
        $scope.editer = function(eleve){
            $http.get('rest/eleves/getEleveParId/'+eleve.id).then(function(data){
                $scope.eleve = data.data;
            })
         }
 
        $scope.validerEdition = function(){
            $http.post('rest/eleves/validerEdition/',$scope.eleve).then(function(data){
                alert('modifié');
                rafraichirLeTableau();
            })
         }
 
        $scope.ajouter = function(){
            $http.post('rest/eleves/ajouterEleve/',$scope.nouvelEleve).then(function(data){
                alert('ajouté');
                rafraichirLeTableau();
            })
         }
 
         $scope.supprimer = function(eleve){
            $http.get('rest/eleves/supprimerEleveParId/'+eleve.id).then(function(data){
                alert('supprimé');
                rafraichirLeTableau();
            })
         }
 
         rafraichirLeTableau();
/* FIN DU CONTROLEUR */
});