# MeetSpain, Faculty of Electrical Engineering, 2023

Application specification

A simple android application that represents geographic handbook for arbitrary
the state. The application consists of at least the following categories:
- Cities
- Landmarks
- News
- In general
- Settings


Cities - part of the application that displays a list of at least ten cities. For
For each city, it is possible to see more detailed information, which includes a textual description (e.g.
history, number of inhabitants, geographical location, etc.), images of the city (uploaded from some public
available websites), as well as a video presentation (reproduce within the application, from someone
from streaming services), if it exists for a given city. In addition, it is necessary to enable the display
weather forecasts for the selected city, where one of the existing services can be used
which provide weather forecast information. The design of the weather forecast page should
be arbitrary. Enable the display of all cities on the map.
Sights - part of the application that displays a list of the main sights and
attraction of the chosen country. Allow the user to mark an item as "favorite", eg
for which it is added to the favorites list. In addition, the application should enable i
view items on the map, using pins. Item details should also be accessible via
of the corresponding pin.
News - part of the application that displays a list of news related to the selected country
which must be downloaded from the appropriate service. It is possible to use the ready-made service
(eg newsapi.org), or create your own service in an arbitrary way. Except for downloading
news when the application is in online mode, it is necessary to enable reading news even in offline mode
mode, so that the last read news will be cached on the device, in an adequate way.
General – part of the application that displays basic information for the selected one
country, such as history, capital city, flag, etc.
Settings - enable language selection, caching settings, minimum number selection
image that is loaded for a city (when the user is in the city details).
Localization/internationalization needs to be supported and enabled
that the application works at least in Serbian and English. It is not necessary to translate
content that is downloaded for remote locations.
