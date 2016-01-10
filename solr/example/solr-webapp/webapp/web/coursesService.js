app.factory('coursesService', function ($http, $rootScope) {

    var url = "http://localhost:8983/solr/collection1/select?q=*%3A*&rows=100000&wt=json&callback=JSON_CALLBACK"
    return {
        all: function() {
            return $http.get(url).then(function(result){
                return result.data;
            });
        }
    }

});
