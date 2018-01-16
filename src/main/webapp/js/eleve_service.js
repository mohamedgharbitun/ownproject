'use strict';
 
angular.module('MonApp')
.factory('EleveService', ['$http', '$q', function($http, $q){

	var factory = {
    	totalEleves: totalEleves,
    	getEleveParId: getEleveParId,
    	validerEdition:validerEdition,
    	ajouterEleve:ajouterEleve,
    	supprimerEleveParId:supprimerEleveParId
    };
 
    return factory;
 
    function totalEleves() {
        var deferred = $q.defer();
        $http.get('rest/eleves/totalEleves')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching eleves');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getEleveParId(eleve) {
        var deferred = $q.defer();
        $http.get('rest/eleves/getEleveParId/'+eleve.id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching eleve');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function validerEdition(eleve) {
        var deferred = $q.defer();
        $http.post('rest/eleves/validerEdition/', eleve)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while validating eleve User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function ajouterEleve(nouvelEleve) {
        var deferred = $q.defer();
        $http.post('rest/eleves/ajouterEleve/',nouvelEleve)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Eleve');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function supprimerEleveParId(eleve) {
        var deferred = $q.defer();
        $http.delete('rest/eleves/supprimerEleveParId/'+ eleve.id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Eleve');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
}]);