
var app = angular.module('aroundyou', ['ui.bootstrap']);

app.controller('ShowNews', function($scope, $http) {

	$scope.showNews = function(section) {
		var url = "/aroundyou/news/section?sectionName="+section;
		var config = {
				headers: {
					'Content-Type': 'application/json; charset=utf-8;'
				}
		}

		$http.get(url, config).then(function(data, status) {
			$scope.records = data.data;
			$scope.totalItems = $scope.records.length;
			$scope.currentPage = 1;
			$scope.itemsPerPage = 20;
		}, function error(data, status) {
			console.log(status);
			$scope.dataView = false;
		});
	};

	$scope.showNewsBySourceAndSection = function(source,section) {
		var url = "/aroundyou/news/source?source="+source+"&section="+section;
		var config = {
				headers: {
					'Content-Type': 'application/json; charset=utf-8;'
				}
		}

		$http.get(url, config).then(function(data, status) {
			$scope.records = data.data;
			$scope.totalItems = $scope.records.length;
			$scope.currentPage = 1;
			$scope.itemsPerPage = 20;
		}, function error(data, status) {
			console.log(status);
			$scope.dataView = false;
		});
	};


	$scope.showNewsByPeriodAndSection = function(period,section) {
		var url = "/aroundyou/news/period?period="+period+"&section="+section;
		console.log(url);
		var config = {
				headers: {
					'Content-Type': 'application/json; charset=utf-8;'
				}
		}
		$http.get(url, config).then(function(data, status) {
			$scope.records = data.data;
			$scope.totalItems = $scope.records.length;
			$scope.currentPage = 1;
			$scope.itemsPerPage = 20;
		}, function error(data, status) {
			console.log(status);
			$scope.dataView = false;
		});
	};

	$scope.searchNews = function() {
		var url = "/aroundyou/news/search?text="+$scope.search;
		console.log(url);
		var config = {
				headers: {
					'Content-Type': 'application/json; charset=utf-8;'
				}
		}

		$http.get(url, config).then(function(data, status) {
			$scope.records = data.data;
			$scope.totalItems = $scope.records.length;
			$scope.currentPage = 1;
			$scope.itemsPerPage = 20;
		}, function error(data, status) {
			console.log(status);
			$scope.dataView = false;
		});
	};

});
