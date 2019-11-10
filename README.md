# Media Publication AEM project

This is a Media Publication AEM project built on maven AEM archetype 13.
This project is deployed and tested on AEM 6.3.

## Modules

The main parts of the template are:

* core: Java bundle containing the core functionality basically WCMUSEPojo's for backend functionality.
* ui.apps: contains the /apps of the project, i.e the UI part, its html,CSS clientlibs, components, templates.
* ui.content: contains media-asset-cards and media-asset-details pages using the components from the ui.apps and sample dam assets i.e images, videos and pdf.

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
	
## To upload Media Publication assets

Go to path- http://localhost:4502/assets.html/content/dam/media-publication and upload assets of type image,pdf or video.
This folder (media-publication) is mapped with the custom metadataschema as a part of requirement. 

    After uploading each asset, author the required custom properties in 'Media Publication' tab of asset properties.
	
	A custom metadataschema is created and mapped to 'media-publication' dam folder for above properties.

## To view Assets cards page

Go to path- http://localhost:4502/content/mediaPublication/media-asset-cards.html?wcmmode=disabled
This will show each of the asset present in 'media-publication' folder in card format.

    Each card will show the image thumbnail and its file name.

	On click of each card will take you to its detail page showing asset details.
	
	Click of 'Download' button will download the asset on your local system.
	
	Click of 'Ok' button will take you back to asset-cards page again.
	
