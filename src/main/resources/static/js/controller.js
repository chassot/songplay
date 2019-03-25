'use strict';

App.controller('TrackController', [
		'$scope',
		'DataService',
		function($scope, DataService) {

			var self = this;

			self.track = {
				id : null,
				trackname : '',
				author : '',
				duration : ''
			};

			self.album = {
				id : null,
				name : '',
				author : '',
				year : ''
			};

			self.albums = [];
			self.tracks = [];
			self.currentAlbum = {};

			self.fetchAllTracks = function() {
				DataService.fetchAllTracks().then(function(response) {
					self.tracks = response;
				}, function(errResponse) {
					console.error('Error while fetching Tracks.');
				});

				DataService.fetchAllAlbums().then(function(response) {
					self.albums = response;
				}, function(errResponse) {
					console.error('Error while fetching Albums')
				})
			};

			self.fetchTrackByName = function(trackname) {
				DataService.fetchTrackByName(trackname).then(
						function(response) {
							self.tracks = response;
						}, function(errResponse) {
							console.error('Error while fetching Tracks.');
						});
			};

			self.createTrack = function(track) {
				DataService.createTrack(track).then(self.fetchAllTracks,
						function(errResponse) {
							console.error('Error while creating Track.');
						});
			};

			self.updateTrack = function(track, id) {
				DataService.updateTrack(track, id).then(self.fetchAllTracks,
						function(errResponse) {
							console.error('Error while updating Track.');
						});
			};

			self.deleteTrack = function(id) {
				DataService.deleteTrack(id).then(self.fetchAllTracks,
						function(errResponse) {
							console.error('Error while deleting Track.');
						});
			};

			self.fetchAllTracks();

			// Form Operations

			self.submit = function() {
				if (self.track.id == null) {
					console.log('Saving new Track', self.track);
					self.createTrack(self.track);
				} else {
					self.updateTrack(self.track, self.track.id);
					console.log('Track updated with id: ', self.track.id);
				}
				self.reset();
			};

			self.reset = function() {
				self.track = {
					id : null,
					username : '',
					address : '',
					email : ''
				};
				$scope.trackForm.$setPristine();
			};

			// Grid Operations

			self.filter = function() {
				if (self.filterByTrackname == null) {
					self.fetchAllTracks();
				} else {
					self.fetchTrackByName(self.filterByTrackname);
				}
			}

			self.edit = function(id) {
				console.log('Track to be edited: ', id);
				for (var i = 0; i < self.tracks.length; i++) {
					if (self.tracks[i].id == id) {
						self.track = angular.copy(self.tracks[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('Track to be deleted: ', id);
				if (self.track.id === id) {
					self.reset();
				}
				self.deleteTrack(id);
			};

			//Album operations

			self.fetchAllAlbums = function() {
				DataService.fetchAllAlbums().then(function(response) {
					self.albums = response;
				}, function(errResponse) {
					console.error('Error while fetching Tracks.');
				});
			};

			self.fetchAlbumByName = function(albumName) {
				DataService.fetchAlbumByName(albumName).then(
						function(response) {
							self.tracks = response;
						}, function(errResponse) {
							console.error('Error while fetching Tracks.');
						});
			};

			self.createAlbum = function(album) {
				DataService.createAlbum(album).then(self.fetchAllAlbums,
						function(errResponse) {
							console.error('Error while creating Track.');
						});
			};

			self.updateAlbum = function(album, id) {
				DataService.updateAlbum(album, id).then(self.fetchAllAlbums,
						function(errResponse) {
							console.error('Error while updating Track.');
						});
			};

			self.deleteAlbum = function(id) {
				DataService.deleteALbum(id).then(self.fetchAllAlbums,
						function(errResponse) {
							console.error('Error while deleting Track.');
						});
			};

			self.fetchAllAlbums;

			// Form Operations

			self.submitAlbum = function() {
				if (self.album.id == null) {
					self.createAlbum(self.album)
				} else {
					self.updateAlbum(self.album, self.album.id);
					console.log('Album updated with id: ', self.album.id);
				}
				self.reset();
			};

			self.resetAlbum = function() {
				self.album = {
					id : null,
					author : '',
					name : '',
					year : ''
				};
				$scope.albumForm.$setPristine();
			};

			// Grid Operations

			self.filterAlbum = function() {
				if (self.filterByAlbumName == null) {
					self.fetchAllAlbums();
				} else {
					self.fetchAlbumByName(self.filterByAlbumName);
				}
			}

			self.editAlbum = function(id) {
				console.log('Album to be edited: ', id);
				for (var i = 0; i < self.albums.length; i++) {
					if (self.albums[i].id == id) {
						self.album = angular.copy(self.album[i]);
						break;
					}
				}
			};

			self.removeAlbum = function(id) {
				console.log('Album to be deleted: ', id);
				if (self.album.id === id) {
					self.reset();
				}
				self.deleteAlbum(id);
			};
		} ]);