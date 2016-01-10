app.factory('coursesService', function ($http, $rootScope) {

    var url = "http://localhost:8983/solr/collection1/select?q=*%3A*&rows=100000&wt=json&callback=JSON_CALLBACK"
    var url_base = "http://localhost:8983/solr/collection1/select?q="
    var url_end = "&rows=100000&wt=json&callback=JSON_CALLBACK"
    return {
        all: function() {
            return $http.get(url).then(function(result){
                return result.data;
            });
        },
        query: function(q){
            return $http.get(url_base+q+url_end).then(function(result){
                return result.data;
            });
        },
        related: function(id){
            var url_base = "http://localhost:8983/solr/multi?q=id%3A";
            var url_end=  "&wt=json&indent=true&rows=4";
            var url = url_base+id+url_end;
            return $http.get(url).then(function(result){
                return result.data;
            })
        }
    }

});
