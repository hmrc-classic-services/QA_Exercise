#QA Exercise

## General Guidance:
	- Upload the template projects to a private gitlab repository
	- Add @RCDTS as a developer on the created project.
	- All commit messages should follow the template of:
		SDE-{TASK_ID + Unique 3 digit code}: {COMMIT MESSAGE}
		e.g. SDE-0001: Uploaded initial project template
	- Ensure that any added functionality or corrected errors have appropriately named JUnit tests to ensure they work. 
	- Where instructions are vague, reasonable assumptions should be made and documented.

	The app can be run using the run_app.sh file and will be accessible from localhost:8090.
	These tests will need to be implemented in the QA_Test project and should follow similiar patterns to those already present in the tests.

## Task 1:
		GIVEN I am a user
		WHEN I type in the URL for the "addEvent" page
		THEN I want to be on the "addEvent" page




## Task 2:
		GIVEN I am a tester
		AND I have been provided the endpoint spec for the create event endpoint
		WHEN I provide 5 different combinations of event details to the create event endpoint
		THEN I want these details to be visible on the index page

		For this test please create the 5 sets of data you wish to use.

		A basic spec for the create event endpoint follows:
		URL: 
			http://localhost:8090/events/add
		HTTP Method & Query params:
			GET
				Query Params:
					eventName (String)
					eventDate (String in the format yyyy-MM-dd HH-mm)
					eventType (String)
					eventDesc (String)
		Behaviour:
			If successful the application will redirect the user to the main page (and the event should be visible)
			If unsuccessful the application will redirect the user to a general error page





## Task 3:
		GIVEN I am a tester
		AND the above specification is unchanged
		WHEN I test combinations of the above query params
		THEN I want to create a series of test issue reports confirming to the below template

		The template for reporting a test issue is as follows:
		ID: {SURNAME} - {unqiue 4 digit code}
		Summary: {A summary of the issue encountered}
		Can be replicated:
			TRUE / FALSE
		Steps Taken: 
			- {If this is done via an automated test, please provide the code line that it occurs and the steps before and after}
			- {If this is a manual test please outline all steps taken from a fresh instance of the application to create this error}
		Version of the code the issue was found on:
			{please use the latest git commit ID}