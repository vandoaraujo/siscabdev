var accuracies = [ { desc : "Localização desconhecida", zoom :  0 },
                   { desc : "País",                     zoom :  2 },
                   { desc : "Região",                   zoom :  4 },
                   { desc : "Sub-região",               zoom :  7 },
                   { desc : "Cidade",                   zoom : 12 },
                   { desc : "Código Postal",            zoom : 13 },
                   { desc : "Intersection",             zoom : 14 },
                   { desc : "Endereço",                 zoom : 15 },
                   { desc : "Elemento",                 zoom : 16 },
                 ];

var status=[];
status[G_GEO_SUCCESS]            = "Successo";
status[G_GEO_MISSING_ADDRESS]    = "Endereço inexistente";
status[G_GEO_UNKNOWN_ADDRESS]    = "Endereço desconhecido";
status[G_GEO_UNAVAILABLE_ADDRESS]= "Endereço não disponível";
status[G_GEO_BAD_KEY]            = "Chave inválida";
status[G_GEO_TOO_MANY_QUERIES]   = "Número máximo de consultas ultrapassado";
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
		message  = "Geocodificação reversa não localizou um endereço válido\n";
		message += "para a Latitude/Longitude: " + latlng.toUrlValue()
		geocoder.getLocations(latlng, addAddressesToMap);
	}
}

function showLocation2(prstOrigem, prstValor, nome, logradouro, numero, bairro, municipio) {				
	message  = "Não foi possível Geocodificar o endereço informado.";
	geocoder.getLocations(prstValor, addAddressesToMap);
	document.getElementById("label").innerHTML = prstOrigem;
	document.getElementById("nome").innerHTML = nome;
	document.getElementById("logradouro").innerHTML = logradouro;
	document.getElementById("numero").innerHTML = numero;
	document.getElementById("bairro").innerHTML = bairro;
	document.getElementById("municipio").innerHTML = municipio;
}

function showLocation() {				
	message  = "Não foi possível Geocodificar o endereço informado.";
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
	message += '<b>Endereço:</b><br/>';
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
	         + "Acurácia: " + accuracy + " ("
	                        + accuracies[accuracy].desc + ")"  + "<br/>"
	         + "LatLng: " + point.lat() + ", " + point.lng();

	CoordX  = point.lat();	
	CoordY  = point.lng();	

	document.getElementById("CoordX").innerHTML = CoordX;
	document.getElementById("CoordY").innerHTML = CoordY;
	
	document.getElementById("details").innerHTML = details;
}