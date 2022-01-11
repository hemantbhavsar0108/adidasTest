package constant;

public interface IFrameworkConstants {
	  /**
	   * Enum of browsers supported
	   */
	  public enum Browser {
	      FIREFOX("Firefox"),
	      CHROME("Chrome"),
	      IE("IE");

	      private String name;

	      Browser(String name) {
	          this.name = name;
	      }

	      public String toString() {
	          return name;
	      }
	  }
	  
	  /*
	   * 
	   */
	  
	  enum ConfigKeyWords {

	      CONFIG_PROPERTIES_PATH("/Configuration/Config.properties"),
	      OBJECT_PROPERTIES_PATH("ObjectPropertiesPath"),
	      BROWSER("Browser"),
	      ENVIRONMENT("Environment"),
	      RELEASE("Release"),
	      TIMEZONE("Timezone"),
	      USER("User"),
	      CONFIGPROPERTY("ConfigProperty"),
	      OBJECTPROPERTY("ObjectProperty"),
	      REPORT_LOCATION("ReportLocation"),
	      CHROME_DRIVER("ChromeDriver"),
	      GECKO_DRIVER("GeckoDriver"),
	      IE_DRIVER("IEDriver"),
	      
	      LOGIN_USERNAME("LoginUsername"),
	      LOGIN_PASSWORD("LoginPassword"),
	  	URL("URL"),
	  	EMAIL("Email"),
	  	PASSWORD("Password");
	  	
	      
	      //private final String value;
		  private String value;
	      ConfigKeyWords(final String value) {
	          this.value = value;
	      }

	      public String toString() {
	          return value;
	      }
	  }
	  
	  
	  enum Locator{

	      ID("id"),
	      NAME("name"),
	      CLASSNAME("class"),
	      TAGNAME("tagname"),
	      LINKTEXT("linkText"),
	      PARTIALLINKTEXT("partialLinkText"),
	      XPATH("xpath"),
	      CSSSelector("cssSelector");
	      //private final String value;
		  private String value;
		  Locator(final String value) {
	          this.value = value;
	      }

	      public String toString() {
	          return value;
	      }
	  }

	  enum ExplicitWaitCondition{
		  AlertIsPresent("alertIsPresent"),
		  ElementSelectionStateToBe("elementSelectionStateToBe"),
		  ElementToBeClickable("elementToBeClickable"),
		  ElementToBeSelected("elementToBeSelected"),
		  FrameToBeAvaliableAndSwitchToIt("frameToBeAvaliableAndSwitchToIt"),
		  InvisibilityOfTheElementLocated("invisibilityOfTheElementLocated"),
		  InvisibilityOfElementWithText("invisibilityOfElementWithText"),
		  PresenceOfAllElementsLocatedBy("presenceOfAllElementsLocatedBy"),
		  PresenceOfElementLocated("presenceOfElementLocated"),
		  TextToBePresentInElement("textToBePresentInElement"),
		  TextToBePresentInElementLocated("textToBePresentInElementLocated"),
		  TextToBePresentInElementValue("textToBePresentInElementValue"),
		  TitleIs("titleIs"),
		  TitleContains("titleContains"),
		  VisibilityOfElementLocated("visibilityOfElementLocated") ;
		  
		  public String value;
		  
		  ExplicitWaitCondition(final String value) {
	          this.value = value;
	      }

	      public String toString() {
	          return value;
	      }
	  }

	  enum TestExecutionStatus{
		  
		  PASS("Pass"),
		  FAIL("Fail"),
		  NOTCOMPLETED("NotCompleted");
		  public String value;
		  
		  TestExecutionStatus(final String value) {
	          this.value = value;
	      }

	      public String toString() {
	          return value;
	      }
	  }
	  
	  enum ApplicationObjectText{
		  
		  NextButton("Next"),
		  AddToCart("Add to cart"),
		  CartPage("Cart"),
		  HomePage("Home"),
		  PlaceOrderLink("Place Order");
		
		  public String value;
		  
		  ApplicationObjectText(final String value) {
	          this.value = value;
	      }

	      public String toString() {
	          return value;
	      }
	  }


}
