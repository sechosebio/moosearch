app.controller('homeController',function($scope,$uibModal,$log,coursesService){


    /*$scope.courses = [
      {
        id: "v1-228",
        description: "For anyone who would like to apply their technical skills to creative work ranging from video games to art installations to interactive music, and also for artists who would like to use programming in their artistic practice.",
        name: "Programación Creativa para Medios Digitales y Aplicaciones Móviles",
        subtitleLanguages: [
          "en",
          "kk"
        ],
        slug: "digitalmedia",
        courseType: "v1.session",
        primaryLanguages: [
          "en"
        ],
        owner: "Coursera",
        photoUrl: "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://d15cw65ipctsrr.cloudfront.net/24/63a093e763b307dc9420e796aeb06a/GoldComputing3.jpg"
      },
      {
        id: "69Bku0KoEeWZtA4u62x6lQ",
        slug: "gamification",
        description: "Gamification is the application of game elements and digital game design techniques to non-game problems, such as business and social impact challenges. This course will teach you the mechanisms of gamification, why it has such tremendous potential, and how to use it effectively. For additional information on the concepts described in the course, you can purchase Professor Werbach's book For the Win: How Game Thinking Can Revolutionize Your Business in print or ebook format in several languages.",
        courseType: "v2.ondemand",
        subtitleLanguages: [
          "uk",
          "zh-CN",
          "vi",
          "tr",
          "kk"
        ],
        primaryLanguages: [
          "en"
        ],
        name: "Ludificación",
        owner: "Coursera",
        photoUrl: "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera.s3.amazonaws.com/topics/gamification/large-icon.png"
    }];*/

    coursesService.all().then(function(data){

        $scope.courses = data.response.docs;
    })

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

  $scope.toggleAnimation = function () {
    $scope.animationsEnabled = !$scope.animationsEnabled;
  };


});

app.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance,item) {

    $scope.course = item;

  $scope.ok = function () {
    $uibModalInstance.close($scope.course);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
};

});
