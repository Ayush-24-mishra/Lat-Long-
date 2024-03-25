const locationElement = document.getElementById('location');
const latitudeElement = document.getElementById('latitude');
const longitudeElement = document.getElementById('longitude');

function displayLocation(location) {
    latitudeElement.textContent = location.coords.latitude;
    longitudeElement.textContent = location.coords.longitude;
}

function getLocationError(error) {
    console.error(error.message);
}

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(displayLocation, getLocationError);
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
}

// Update the user's location every 5 seconds
setInterval(getLocation, 5000);

// Get the initial location
getLocation();