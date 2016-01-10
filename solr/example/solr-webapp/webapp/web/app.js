var app = angular.module('mooSearch',
    ['ui.bootstrap',
    'ngRoute'
]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
       controller:'homeController',
       templateUrl: 'index.html'
   });

   $routeProvider.otherwise(
       {redirectTo: '/'}
   );
}]);

app.run(function($log){
    $log.debug("starterApp + ngMaterial running...");
});
