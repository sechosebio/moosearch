app.controller('homeController',function($scope,$uibModal,$log,$http,coursesService){

    $http.get("http://localhost:8983/solr/collection1/select?q=*%3A*&fl=tipo&wt=json&indent=true")
    .then(function(response){
        $scope.avaliableCategories = response.data.response.docs;
    });

    $scope.open = function (course) {
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            size: 'lg',
            resolve: {
                item : course
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    $scope.openHowTo = function () {
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'myModalContentHowTo.html',
            controller: 'ModalHowToInstanceCtrl',
            size: 'lg'
        });

        modalInstance.result.then(function () {

        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animationsEnabled;
    };

    $scope.selectedCourse = undefined;
    var url_base = "http://localhost:8983/solr/collection1/select?q="
    var url_end = "&rows=100000&wt=json"
    $scope.getCourses = function(val) {
        var url = url_base+"nombre%3A"+val+"*+OR+descripcion%3A"+val+url_end;
        return $http.get(url).then(function(response){
            return response.data.response.docs.map(function(item){

                return item;
            });
        });
    };

    var query = null;
    function crearQuery(q){
        if(query===null){
            query=q
        }else{
            query+="+AND+"+q;
        }
        return query;
    }

    function validarTexto(t){
        //separamos en array
        var aux="descripcion:("
        var primera = t[0];
        var ultima = t.substr(t.length-1);
        var res = t.split(" ");
        var text = "";
        if(primera=='"'&&ultima=='"'){
            var a = t.replace(" ","+");
            text = "descripcion:"+a;
            return text;
        }else{
            if(res.length>1){
                //recorremos array
                for (i = 0; i < res.length; i++) {
                    text += res[i]+"+OR+";
                }
                var p = text.lastIndexOf("+OR+");
                var sub = text.substr(0, p);
                aux+=sub+")";
                return aux;
            }else{
                text = "descripcion:"+res[0];
                return text;

            }
        }
    }

    $scope.search = function(){
        var vacio = "*%3A*";
        var url = "http://localhost:8983/solr/collection1/select?&wt=json&rows=100000&indent=true&q=";

        if($scope.checkBoxCoursera && $scope.checkBoxUdacity){
            console.log("ambos");
            var q = "pagina:(udacity+OR+coursera)";
            crearQuery(q);
        }else if($scope.checkBoxCoursera){
            console.log("cousera");
            var q = "pagina:coursera";
            crearQuery(q);
        }else if($scope.checkBoxUdacity){
            console.log("udacity");
            //var url = "http://localhost:8983/solr/collection1/select?q=pagina%3A+udacity%0A&rows=100000&wt=json&indent=true"
            var q = "pagina:udacity"
            crearQuery(q);
        }
        if($scope.language){
            var q = "idiomas:"+$scope.language;
            crearQuery(q);
        }
        if($scope.category){
            var q = "tipo:"+$scope.category;
            crearQuery(q);
        }
        if($scope.text){
            var con = validarTexto($scope.text);
            crearQuery(con);
        }
        if(query===null){
            crearQuery(vacio);
        }

        console.log(query);
        url=url+query;
        //console.log(url);
        return $http.get(url).then(function(result){
            $scope.courses = result.data.response.docs;
            query = null;
        });

    };


});

app.controller('ModalHowToInstanceCtrl', function ($scope, $uibModalInstance) {



    $scope.ok = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };


});

app.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance,coursesService,item) {

    $scope.course = item;

    coursesService.related(item.id).then(function(data){

        $scope.relatedCourses = data.response.docs;
    });

    $scope.ok = function () {
        $uibModalInstance.close($scope.course);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.change = function(related){
        $scope.course = related;
        coursesService.related(related.id).then(function(data){

            $scope.relatedCourses = data.response.docs;
        });

    }

});
