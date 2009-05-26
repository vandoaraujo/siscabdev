var accuracies = [ { desc : "Localiza��o desconhecida", zoom :  0 },
                   { desc : "Pa�s",                     zoom :  2 },
                   { desc : "Regi�o",                   zoom :  4 },
                   { desc : "Sub-regi�o",               zoom :  7 },
                   { desc : "Cidade",                   zoom : 12 },
                   { desc : "C�digo Postal",            zoom : 13 },
                   { desc : "Intersection",             zoom : 14 },
                   { desc : "Endere�o",                 zoom : 15 },
                   { desc : "Elemento",                 zoom : 16 },
                 ];

var status=[];
status[G_GEO_SUCCESS]            = "Successo";
status[G_GEO_MISSING_ADDRESS]    = "Endere�o inexistente";
status[G_GEO_UNKNOWN_ADDRESS]    = "Endere�o desconhecido";
status[G_GEO_UNAVAILABLE_ADDRESS]= "Endere�o n�o dispon�vel";
status[G_GEO_BAD_KEY]            = "Chave inv�lida";
status[G_GEO_TOO_MANY_QUERIES]   = "N�mero m�ximo de consultas ultrapassado";
status[G_GEO_SERVER_ERROR]       = "Erro no Servidor"

var map      = null;
var geocoder = null;
var message  = null;
var q        = document.getElementById("q");

function init() {
	if (GBrowserIsCompatible()) {
		map = new GMap2(document.getElementById("map_canvas"));
		map.setCenter(new GLatLng(-22.9049164, -43.1098755), 16);
		map.addControl(new GSmallZoomControl());
		geocoder = new GClientGeocoder();
		GEvent.addListener(map, "click", clicked);
		geocoder.getLocations(q.value, addAddressesToMap);
		//map.openInfoWindow(map.getCenter(), "Click the map!");		

	}
}

function clicked(overlay, latlng) {
	if (latlng) {
		message  = "Geocodifica��o reversa n�o localizou um endere�o v�lido\n";
		message += "para a Latitude/Longitude: " + latlng.toUrlValue()
		geocoder.getLocations(latlng, addAddressesToMap);
	}
}

function showLocation2(prstOrigem, prstValor, nome, logradouro, numero, bairro, municipio) {				
	message  = "N�o foi poss�vel Geocodificar o endere�o informado.";
	geocoder.getLocations(prstValor, addAddressesToMap);
	document.getElementById("label").innerHTML = prstOrigem;
	document.getElementById("nome").innerHTML = nome;
	document.getElementById("logradouro").innerHTML = logradouro;
	document.getElementById("numero").innerHTML = numero;
	document.getElementById("bairro").innerHTML = bairro;
	document.getElementById("municipio").innerHTML = municipio;
}

function showLocation() {				
	message  = "N�o foi poss�vel Geocodificar o endere�o informado.";
	geocoder.getLocations(q.value, addAddressesToMap);
}

function addAddressesToMap(addresses) {
	if(addresses.Status.code != G_GEO_SUCCESS) {
		alert(message + "\nStatus: " + status[addresses.Status.code]);
		return;
	}

	address  = addresses.Placemark[0];
	q.value  = address.address;
	accuracy = address.AddressDetails.Accuracy
	point    = new GLatLng(address.Point.coordinates[1],
						   address.Point.coordinates[0]);
	map.clearOverlays();
	marker = new GMarker(point);
	map.addOverlay(marker);
	map.setCenter(point, accuracies[accuracy].zoom);

	message  = '<div class="infowindow">';
	message += '<b>Endere�o:</b><br/>';
	message += address.address.replace(/ - /g, "<br/>");
	message += '</div>';
	marker.openInfoWindowHtml(message);

	fakeThoroughfare = {"ThoroughfareName": "-"};
	fakePostalCode   = {"PostalCodeNumber": "-"};
	fakeLocality     = {"LocalityName" : "-","Thoroughfare" : fakeThoroughfare,"PostalCode" : fakePostalCode};

	country    = address.AddressDetails.Country;
	admArea    = (country.AdministrativeArea) ? country.AdministrativeArea : {"AdministrativeAreaName": "-","Locality": fakeLocality};
	locality   = (admArea.Locality) ? admArea.Locality : fakeLocality;
	postalCode = (locality.PostalCode) ? locality.PostalCode : fakePostalCode;
	thoroughfare = locality.Thoroughfare ? locality.Thoroughfare : fakeThoroughfare;

	details  = "Rua     : " + thoroughfare.ThoroughfareName    + "<br/>"
	         + "Cidade  : " + locality.LocalityName            + "<br/>"
	         + "Estado  : " + admArea.AdministrativeAreaName   + "<br/>"
	         + "Pais    : " + country.CountryName + " ("
	                        + country.CountryNameCode + ")"    + "<br/>"
	         + "CEP     : " + postalCode.PostalCodeNumber      + "<br/><br/>"
	         + "Acur�cia: " + accuracy + " ("
	                        + accuracies[accuracy].desc + ")"  + "<br/>"
	         + "LatLng: " + point.lat() + ", " + point.lng();

	CoordX  = point.lat();	
	CoordY  = point.lng();	

	document.getElementById("CoordX").innerHTML = CoordX;
	document.getElementById("CoordY").innerHTML = CoordY;
	
	document.getElementById("details").innerHTML = details;
}