'use strict';

App.factory('DataService', ['$http', '$q', function($http, $q){
    return {
        fetchAllTracks: function() {
            return $http.get('http://localhost:8080/track/').then(
                function(response) {
                    return response.data;
                },
                function(errResponse) {
                    console.error('Error while fetching tracks');
                    return $q.reject(errResponse);
                });
            },

        fetchTrackByName: function(trackname) {
            return $http.get('http://localhost:8080/track/' + trackname).then(
                function(response) {
                    return response.data;
                },
                function(errResponse) {
                    console.error('Error while fetching tracks');
                    return $q.reject(errResponse);
                });
            },

        createTrack: function(track){
            return $http.post('http://localhost:8080/track/', track).then(
                function(response) {
                    return response.data;
                },
                function(errResponse) {
                    console.error('Error while creating track');
                    return $q.reject(errResponse);
                });
            },

        updateTrack: function(track, id){        	
            return $http.put('http://localhost:8080/track/' + id, track).then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while updating track');
                    return $q.reject(errResponse);
                });
            },

            deleteTrack: function(id){
            	return $http.delete('http://localhost:8080/track/' + id).then(
	                function(response){
	                    return response.data;
	                },
	                function(errResponse){
	                    console.error('Error while deleting track');
	                    return $q.reject(errResponse);
	                });
            },
            fetchAllAlbums: function() {
                return $http.get('http://localhost:8080/album/').then(
                    function(response) {
                        return response.data;
                    },
                    function(errResponse) {
                        console.error('Error while fetching tracks');
                        return $q.reject(errResponse);
                    });
                },

            fetchAlbumByName: function(albumName) {
                return $http.get('http://localhost:8080/album/' + albumName).then(
                    function(response) {
                        return response.data;
                    },
                    function(errResponse) {
                        console.error('Error while fetching tracks');
                        return $q.reject(errResponse);
                    });
                },

            createAlbum: function(album){
                return $http.post('http://localhost:8080/album/', album).then(
                    function(response) {
                        return response.data;
                    },
                    function(errResponse) {
                        console.error('Error while creating track');
                        return $q.reject(errResponse);
                    });
                },

            updateAlbum: function(album, id){
                return $http.put('http://localhost:8080/emusic-corp/track/' + id, album).then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating track');
                        return $q.reject(errResponse);
                    });
                },

            deleteAlbum: function(id){
                return $http.delete('http://localhost:8080/album/' + id).then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting track');
                        return $q.reject(errResponse);
                    });
                }
    };
}]);
