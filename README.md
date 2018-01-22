This is just a simple project to illustrate an error that we experience in another more complex project when using onyx with tags. However, the basic structure is the same.

## Usage

1. build the project by invoking the build.sh using ```./build.sh```
2. start all necessary components by invoking the appropriate startup.sh


- startup_simple.sh starts peers with the simple tag provided
- startup_special.sh starts peers with the special tag provided
- startup_job.sh kicks off a job

**Note:** *You may want to change the bind-address within the startup scripts as well as the tenancy id etc.*

## Error-Message
The following error message is shown when a job is kicked off after all required peers are started and registered:

```
INFO [onyx.log.commands.submit-job:92] - Job ID 2a003323-6e5b-8366-db72-3a017052a399 has been submitted with tenancy ID 311b510f-411e-4d83-b3f7-90a7c0c246c6, but received no virtual peers to start its execution.
Tasks each require at least one peer to be started, and may require more if :onyx/n-peers or :onyx/min-peers is set.
If you were expecting your job to start now, either start more virtual peers or choose a different job scheduler, or wait for existing jobs to finish.
```

**Note:** *The job id will change every time. The message above is just copied from stdout.*
