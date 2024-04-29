# Description:
This app's purpoes is to help users manage shared expenses during events such as a grill or a get-together with friends. People can create events, add others to those events and create expenses for those events. The app gives the users an overview of how much money was spent on different categories and helps users settle their debts between each other. It is a very easy to use app, since there is no log in required.

# Features:
- Connect to a server of your choice
- Create a profile on the app 
- Choose the language you want the app to be in (choice persists throughout multiple sessions)
- Create a language of your own choosing for the app to be in
- Create an event
- Join an event via an invite code
- Send others an email (through the app) with the invite code to a certain event
- Edit an event participant's information
- Remove a participant from an event
- Add an expense to an event (specify who made the payment and a list of people who benefitted from that payment)
- Within an event, see a list of debts between people which were made as a result of the created expenses
- Add a tag to an expense (there are three basic tags, but a user can add other tags - tags have a name and a color)
- Edit the details of an expense (people who participated in it or the amount paid or its description or any other detail)
- Within an event, see a statistics page (with a pie chart) which reflects the amount of money spent on different categories (tags) and also how much each person in the group owes/is owed
- In the home page you can see all events and expenses you are part of
- You can download a language template - a list of all the words used in throughout the app - which you can fill and send to the developers so it can be added in the next patch
- As a user, you can go to the settings page where :
  - You can change your first name and last name
  - You can configure your email: you can just write the address or if you want the app to send emails on your behalf, you can also write the password for the email, the smtp and the port number (in the bottom of the page you will get confirmation if the app is able to send emails on your behalf)
  - You can change the server you are on
- There is also an admin side of the app:
  - As an admin, you will see a password in the server terminal which can be written in the appropriate field in the home page
  - When logged in to the admin side, you are able to see some information about the servers
  - You can see all of the created events and the number of participants in each of them (this can help to see how populated an event is) - the numbers are updated in real time, via long polling
  - You can sort the events by their name, their creation date and by the date they were last updated (this way, an admin can see when an event is no longer active)
  - You can delete an event 
  - You can create a backup of the event (in JSON format)
  - You can import an event (in JSON format)
  - The import and backup features are used in case an admin deletes an event by accident

# Running the project
Make sure to have the server running before you run the client.

userconfig file
```toml
address = "http://localhost"
websocket = "ws://localhost"
port = 8080

[mailConfig]
    username = "xxx@gmail.com"
    password = "xxxx"
    host = "smtp.gmail.com"
    port = 587
    smtpAuth = true
    startTls = true
```
You can configure the file to add your email details. You can edit the 'address' which is the http url of the server.
websocket is the websocket url for the server.

running  the server 

`./gradlew bootRun`

running the client

`./gradlew run`

# Live updating
## WebSockets
websockets are used in the event overview page. Websockets live sync all aspects of an event: expenses, participants, name changes etc.

## LongPolling
used in the admin overview table. long polling is used when creating an event, updating an event or deleting it.


# Extensions
## Foreign currency
We have full support for foreign currencies including caching on the server. User can choose their preferred currency whenever they want.
## Statistics page
We have the three colored standard tags as well as ability to create new custom colored tags. 

In the statistics page you can view a piechart with a distribution of tags across all expenses. The piechart colors are based on the tag colors. The piechart contains absolute and relative values shown in your preferred currency.

You can view the sum of all expenses. 

You can view the tags in the expense overview in the event overview page.

There is a dedicated scene for managing tags.
## Live language switch
You can view the current language on the starting page and the eventoverview. You can click on the flag to change the language, very intuitive.

The preferred language gets saved to the config file. You can download the template via the settings page and fill it in to send it to the admins.

The app out of the box supports English, Dutch and Romanian.

## Detailed expenses
You can specify the date for an expense.

You can decide to eplit equally or within a subgroup.

You can filter expenses, per participant, on either from or to.

When clicking on an expense you can view more details about it.
## Email notification
You can use the app without email config, the buttons would be grayed out. email configuration is fully client side.

The user can configure their gmail credentials in the config file.
There is a test button in the invite scene, to see if it worked or not.
Using the invite scene you acn invite participants. They are automatically added if they join using a client. 

You can easily add/edit participants via the event overview page.

To send a paymnent reminder to a participant, click on the participant and click send reminder.



# HCI features
## Multi-modal visualization
Some buttons are colored. For example cancellation buttons are colored red, while confirming/accepting buttons are green.

Some buttons are an icon for easier use, like a settings cog or an icon of a person with a plus sign , resembling add new participant.

## Confirmation popups
When doing an irreversible action, we ask for confirmation from the user, just in case.

## Information popups
Whenever the user does something that needs some information, they get a popup with info. For example changing an event title would popup a window telling you if it is successful or a failure.

If a user enters invalid information they get informed.

## Shortcuts
On the main screen, press the shortcuts button or use the shortcut `Alt + H` to view all available shortcuts and where.

## Navigation
Navigation is logical and consistent througout the application and accross different scenes. All scenes have a back button, so you can go back to the last viewed scene.

Using the shortcuts you can create events, add expenses, change the title and more all without touching the mouse.

shortcuts, logical navigation

# CLI args
to launch directly into admin page use <br>
``--admin=1``

You can access the admin page via the app itself as well
