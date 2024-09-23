# Kinesis

Kinesis allows to ingest, process, and analyse real-time streaming data.

The are two versions of Kinesis:



|            | Kinesis Data Stream                                                 | Kinesis Data Firehose                                                           |
| ---------- | ------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| Purpose    | ingestion of data                                                   | data transfer tool to get information to S3, Redshift, Elasticsearch, or Splunk |
| Speed      | Real time                                                           | near real time (within 60s)                                                     |
| Difficulty | you are are sponsable for crating consumers and scaling the streams | Plug and play with AWS Architecture. Scaling and consumers are handled by AWS!  |

Kinesis Data Stream:&#x20;

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/mo..56Qw57hr?a=5970&#x26;x=79&#x26;y=-10&#x26;w=1767&#x26;h=1096&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20a176c76df5d1240feaa550122bc826b7e81d6088e72e89e545d97aa5ddaae387-ts%3D1726396829" alt=""><figcaption></figcaption></figure>



Kinesis Firehose

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/~y..sptvYpEQ?a=6054&#x26;x=103&#x26;y=-10&#x26;w=1233&#x26;h=1079&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%203aca1f13ff10b47be25406ff77bb0c529ddc6c04812a7cb6e2f2a8799c700bd7-ts%3D1726396829" alt=""><figcaption></figcaption></figure>

## Kinesis Data Analytics

* Analyze data using standard SQL.
* Data Analytics supports both Kinesis Data Stream and Data Firehose.
* There are no services to manage&#x20;
* You only pay for what you are using



