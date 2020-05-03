[How to run application](#how-to-run-application)

[Spanner](#spanner)

***
## How to run application
To run the application, you need to configure Spanner database and make sure your port 8090 is not used.

***

##Spanner


1. Install [Cloud SDK](https://cloud.google.com/sdk/docs#install_the_latest_cloud_tools_version_cloudsdk_current_version)
2. [Initialize the SDK](https://cloud.google.com/sdk/docs/quickstart-windows#initialize_the_sdk)
3. Get [credentials](https://cloud.google.com/docs/authentication/getting-started)

More information:  
https://cloud.google.com/sdk/docs/quickstart-windows
https://cloud.google.com/spanner/docs/getting-started/set-up  
https://cloud.google.com/sdk/docs/authorizing

Add (change) properties to application.properties
```
spring.cloud.gcp.spanner.project-id=[MY_PROJECT_ID]
spring.cloud.gcp.spanner.instance-id=[MY_INSTANCE_ID]
spring.cloud.gcp.spanner.database=[MY_DATABASE]
```

You may go and create a spanner instance [here](https://console.cloud.google.com/spanner/instances)

Alternatively, use this command to create instance via gcloud CLI:

`$ gcloud spanner instances create yourinst --config=regional-europe-west1 --description=your-inst --nodes=1`

To create database, use this command :

`$ gcloud spanner databases create yourdb --instance=yourinst `

To delete database, use this command :

`$ gcloud spanner databases delete hospital --instance=testinst `
***
## Add test data to Spanner

*In progress...*

***
*To be continued...*