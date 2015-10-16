package module4;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of earthquakes
	// per country.
	
	// You can ignore this.  It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	private static final boolean offline = false;
	
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	

	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap map;
	
	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(900, 700, OPENGL);
		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 650, 600, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    //earthquakesURL = "2.5_week.atom";
		}
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// FOR TESTING: Set earthquakesURL to be one of the testing files by uncommenting
		// one of the lines below.  This will work whether you are online or offline
		//earthquakesURL = "test1.atom";
		//earthquakesURL = "test2.atom";
		
		// WHEN TAKING THIS QUIZ: Uncomment the next line
		earthquakesURL = "quiz1.atom";
		
		
		// (2) Reading in earthquake data and geometric properties
	    //     STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		//     STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);            //  в данном случае читаетс€ целый объект, который потом в конструкторе CityMarker парситс€
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) {
		  cityMarkers.add(new CityMarker(city));                                 //ссылка на класс CityMarker
		}
	    
		//     STEP 3: read in earthquake RSS feed
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {
		  //check if LandQuake
		  if(isLand(feature)) {
		    quakeMarkers.add(new LandQuakeMarker(feature)); 
		    
		    //ссылка на класс LandQuakeMarker
		  }
		  // OceanQuakes
		  else {
		    quakeMarkers.add(new OceanQuakeMarker(feature));                                 //ссылка на класс OceanQuakeMarker
		  }
	    }

	    // could be used for debugging
	    printQuakes();
	 		
	    // (3) Add markers to map
	    //     NOTE: Country markers are not added to the map.  They are used
	    //           for their geometric properties
	    map.addMarkers(quakeMarkers);
	    map.addMarkers(cityMarkers);
	    
	    
	    
	}  // End setup
	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();
		
	}
	
	// helper method to draw key in GUI
	// TODO: Update this method as appropriate
	private void addKey() {	
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240);
		rect(25, 50, 150, 250);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Earthquake Key", 50, 75);
		
		fill(color(255, 0, 0));
		ellipse(50, 125, 15, 15);
		fill(color(255, 255, 0));
		ellipse(50, 175, 10, 10);
		fill(color(0, 0, 255));
		ellipse(50, 225, 5, 5);
		
		fill(0, 0, 0);
		text("5.0+ Magnitude", 75, 125);
		text("4.0+ Magnitude", 75, 175);
		text("Below 4.0", 75, 225);
		
		//—јћ «ј’ќ“≈Ћ ѕќ»√–ј“№—я	
		fill(255, 250, 240);
		//rect(25, 350, 300, 50);
		//loadFont("andalemo.ttf");
		//textFont(loadFont("Arial.ttf"));
		//textSize(14);
		//text(textInTotal(), 25, 350);
		//—јћ «ј’ќ“≈Ћ ѕќ»√–ј“№—я		
		
		
	}

	
	
	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {
		
		//берем массив маркеров стран
		//и циклически его обходим в поисках нужного
		for (int index = 0; index < countryMarkers.size(); index ++) {
			//на первой итерации берЄм из списка первый маркер
			Marker singleCounry = countryMarkers.get(index);
			//провер€ем его принадлежность к стране
			//если он находитс€ в стране, метод isInCountry(PointFeature earthquake, Marker country) должен возвратить true в противном случае - false
			//в случае true 
			if(isInCountry (earthquake, singleCounry)) {
				
				isInCountry (earthquake, singleCounry);
				return true;
				
			}
		}
		return false;
	}
	
	
	
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake 
	// feature if it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();
		
		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}



    //prints countries with number of earthquakes
	// You will want to loop through the country markers or country features
	// (either will work) and then for each country, loop through
	// the quakes to count how many occurred in that country.
	// Recall that the country markers have a "name" property, 
	// And LandQuakeMarkers have a "country" property set.
	private void printQuakes() {
	
		// TODO: Implement this method
		//счЄтчик земл€тр€сений в океане дл€ одной страны
		int countOceanQuakesPerCountry = 0;
		
		//счЄтчик земл€тр. в океане общий
		int countOceanQuakesGeneral = 0;
		
		//счЄтчик земл€тр€сений в стране (одельно вз€той)
		int countLandQuakesPerCountry = 0;
		
		//счЄтчик земл€тр€сений общий
		int countQuaqesGeneral = 0;
		
		//им€ страны, где было хот€ бы одно земл€тр€сение
		String countryNameQuakes = null;
		
		//
		String typeOfOceanQuakes = "class module4.OceanQuakeMarker";
		
		String typeOfLandQuakes = "class module4.LandQuakeMarker";
		
		
		for (int index = 0; index < countryMarkers.size(); index ++) {
			
			Marker singleCountry = countryMarkers.get(index);
			
			countryNameQuakes = singleCountry.getStringProperty("name");
			
			for (int indexQuakes = 0; indexQuakes < quakeMarkers.size(); indexQuakes ++) {
				
				Marker singleQuake = quakeMarkers.get(indexQuakes);
				
				String typeOfSingleMrker = singleQuake.getClass().toString();                                        //System.out.println(typeOfSingleMrker);
				
				int comparationOfTypesForOcean = typeOfSingleMrker.compareToIgnoreCase(typeOfOceanQuakes);           //System.out.println(comparationOfTypesForOcean);
				
				int comparationOfTypesForLand = typeOfSingleMrker.compareToIgnoreCase(typeOfLandQuakes);
				
				if(comparationOfTypesForOcean == 0) {
					
					//увеличили счЄтчик 1
					countOceanQuakesPerCountry ++;                                                                    //System.out.println(countOceanQuakesPerCountry);
					
				} else {
					
					String nameOfCountryWithSingleQuake = singleQuake.getStringProperty("country");                   //System.out.println(nameOfCountryWithSingleQuake);
					
					int compareCountries = nameOfCountryWithSingleQuake.compareToIgnoreCase(countryNameQuakes);
					
					if(compareCountries == 0) {
						
						//увеличили счЄтчик 2
						countLandQuakesPerCountry ++;
						
					}
					
					
					
				}
				
				//
				
				
			}
			
			//провер€ем, дл€ данной страны, что накопилось в счЄтчиках
			//печатаем им€ страны
			//счЄтчик океанских
			//счЄтчик земл€ных
			
			//но надо добавить условие, которое говорит: печатать только те страны, где было хот€ бы одно земл€тр€сение
			
			if(countLandQuakesPerCountry > 0 ) {
				
				System.out.println("***************************************");
				System.out.println("Country:" + "\t" + countryNameQuakes);
				System.out.println("Land Quakes in " + countryNameQuakes + " :" +"\t" + countLandQuakesPerCountry);
				//System.out.println("***************");
				
			}
			
			
			//перенЄс выполненные данные по итогу выполнени€ двух циклов нуружу первого цикла
			countOceanQuakesGeneral = countOceanQuakesPerCountry;
			//обнулил счЄтчики
			countOceanQuakesPerCountry = 0;
			countLandQuakesPerCountry = 0;
			
			
		}
		
		System.out.println("***************");
		//System.out.println("Country:" + "\t" + countryNameQuakes);
		System.out.println("Ocean Quakes Total:" + "\t" + countOceanQuakesGeneral);
		//System.out.println("Value of Land    QuakesPerCountry:" + "\t" + countLandQuakesPerCountry);
		System.out.println("***************");		

		
		
	}
	//—јћ «ј’ќ“≈Ћ ѕќ»√–ј“№—я
	public String textInTotal () {
		
		
		// TODO: Implement this method
		//счЄтчик земл€тр€сений в океане дл€ одной страны
		int countOceanQuakesPerCountry = 0;
		
		//счЄтчик земл€тр. в океане общий
		int countOceanQuakesGeneral = 0;
		
		//счЄтчик земл€тр€сений в стране (одельно вз€той)
		int countLandQuakesPerCountry = 0;
		
		//счЄтчик земл€тр€сений общий
		int countQuaqesGeneral = 0;
		
		//им€ страны, где было хот€ бы одно земл€тр€сение
		String countryNameQuakes = null;
		
		//
		String typeOfOceanQuakes = "class module4.OceanQuakeMarker";
		
		String typeOfLandQuakes = "class module4.LandQuakeMarker";
		
		String forOut = null;
		
		//String FromInside = null;
		
		String fromInsideHelp = null;
		
		
		for (int index = 0; index < countryMarkers.size(); index ++) {
			
			Marker singleCountry = countryMarkers.get(index);
			
			countryNameQuakes = singleCountry.getStringProperty("name");                                            String FromInside = null;
			
			for (int indexQuakes = 0; indexQuakes < quakeMarkers.size(); indexQuakes ++) {
				
				Marker singleQuake = quakeMarkers.get(indexQuakes);                                                  
				
				String typeOfSingleMrker = singleQuake.getClass().toString();                                        //System.out.println(typeOfSingleMrker);
				
				int comparationOfTypesForOcean = typeOfSingleMrker.compareToIgnoreCase(typeOfOceanQuakes);           //System.out.println(comparationOfTypesForOcean);
				
				int comparationOfTypesForLand = typeOfSingleMrker.compareToIgnoreCase(typeOfLandQuakes);
				
				if(comparationOfTypesForOcean == 0) {
					
					//увеличили счЄтчик 1
					countOceanQuakesPerCountry ++;                                                                    //System.out.println(countOceanQuakesPerCountry);
					
				} else {
					
					String nameOfCountryWithSingleQuake = singleQuake.getStringProperty("country");                   //System.out.println(nameOfCountryWithSingleQuake);
					
					int compareCountries = nameOfCountryWithSingleQuake.compareToIgnoreCase(countryNameQuakes);
					
					if(compareCountries == 0) {
						
						//увеличили счЄтчик 2
						countLandQuakesPerCountry ++;
						
					}
				}
			}                                                                                                        
			
			//провер€ем, дл€ данной страны, что накопилось в счЄтчиках
			//печатаем им€ страны
			//счЄтчик океанских
			//счЄтчик земл€ных
			
			//но надо добавить условие, которое говорит: печатать только те страны, где было хот€ бы одно земл€тр€сение
			
			if(countLandQuakesPerCountry > 0 ) {
				
				//System.out.println("***************************************");
				//System.out.println("Country:" + "\t" + countryNameQuakes);
				//System.out.println("Land Quakes in " + countryNameQuakes + " :" +"\t" + countLandQuakesPerCountry);
				//System.out.println("***************");
				
				FromInside = "Country :" + "\t" + countryNameQuakes + "\n" + "Land Quakes :" +"\t" + countLandQuakesPerCountry;
				fromInsideHelp = FromInside+ "\n";
				
			}
			
			
			//перенЄс выполненные данные по итогу выполнени€ двух циклов нуружу первого цикла
			countOceanQuakesGeneral = countOceanQuakesPerCountry;
			//обнулил счЄтчики
			countOceanQuakesPerCountry = 0;
			countLandQuakesPerCountry = 0;
			
			 FromInside = null;
		}
		
		//System.out.println("***************");
		//System.out.println("Country:" + "\t" + countryNameQuakes);
		//System.out.println("Ocean Quakes Total:" + "\t" + countOceanQuakesGeneral);
		//System.out.println("Value of Land    QuakesPerCountry:" + "\t" + countLandQuakesPerCountry);
		//System.out.println("***************");
		
		

		forOut = fromInsideHelp + "\n" + "Ocean Quakes Total:" + "\t" + countOceanQuakesGeneral;
		
		
		return forOut;
		
	}
		
}
			

			
	
	
	
	