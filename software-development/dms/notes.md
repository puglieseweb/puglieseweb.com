# Notes

### Create database migration task

The "DO\_NOTHING" mode is particularly useful when:

1. You've pre-created tables with specific configurations (partitioning, indexes, etc.)
2. You want to maintain existing data in the target tables
3. You're migrating to a view or materialized view
4. You have specific constraints or triggers that should remain intact
5. You're doing incremental loads into existing tables



In your multi-stage migration scenario, using "DO\_NOTHING" for the second task makes sense because you already have a properly structured target table and you want DMS to simply populate it with data from your cross-database view.



### Stop task after full load completes

In AWS DMS (Database Migration Service), the "Stop task after full load completes" setting has two variations that control what happens after the initial data load finishes. Let me explain both options:

#### 1. Stop task after full load completes and cached changes have been applied

This option (`StopTaskCachedChangesApplied: true`) means:

* DMS performs the full load of existing data from the source
* During this process, it captures any ongoing changes in a cache
* Once the full load completes, DMS applies all the cached changes
* After the cached changes are applied, the task automatically stops

This is useful when you want:

* A one-time migration with point-in-time consistency
* To ensure all changes that occurred during the full load are applied
* To automatically terminate the task once data is synchronized

#### 2. Stop task after full load completes and cached changes have not been applied

This option (`StopTaskCachedChangesNotApplied: true`) means:

* DMS performs the full load of existing data
* It still captures changes during this process
* Once the full load completes, the task stops immediately
* The cached changes are never applied to the target

This is useful when:

* You only need the initial data snapshot without ongoing changes
* You plan to handle the changes through a different process
* You're doing a staged migration with multiple steps
* You want to inspect the initial load before applying changes

#### In Your Multi-Stage Migration Scenario

In your multi-stage migration with cross-database views:

* For the first task (Oracle to PostgreSQL staging table):
  * You might want to use `StopTaskCachedChangesApplied: true` if you need a complete point-in-time copy including all changes that occurred during migration
* For the second task (view to final table):
  * Since this is likely a one-time operation moving data from the view to the target table, either option could work, but `StopTaskCachedChangesNotApplied: true` is simpler since there shouldn't be changes to the view during this short migration

If you're setting up ongoing CDC (Change Data Capture) replication from Oracle, you would instead use `migration-type: full-load-and-cdc` and leave both of these settings as `false` so the task continues replicating changes after the initial load completes.





### Data Validation&#x20;

Turn off Don't validate data for the table in migration task. Validation with data migration Choose this setting if you want AWS DMS to compare the data at the source and the target immediately after it performs a full data load. Validation ensures that DMS migrated your data accurately, but it requires additional time to complete.
