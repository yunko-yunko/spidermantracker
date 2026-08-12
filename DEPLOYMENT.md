# Cloud Run console deployment

This repository includes a Java 21 `Dockerfile` for Cloud Run and uses the Cloud SQL Java Connector in the `prod` Spring profile.

## Cloud Run service settings

In the Cloud Run **Create service** screen, select **Continuously deploy from a repository**, connect this GitHub repository, and choose **Dockerfile** as the build type.

Use the same region as Cloud SQL (for Seoul, `asia-northeast3`). In **Container(s), Volumes, Networking, Security**, set the following:

| Setting | Value |
| --- | --- |
| Service account | A service account with `Cloud SQL Client` and `Secret Manager Secret Accessor` roles |
| Cloud SQL connections | Select the target PostgreSQL instance |
| `SPRING_PROFILES_ACTIVE` environment variable | `prod` |
| `DB_NAME` environment variable | Cloud SQL database name, e.g. `spiderman` |
| `DB_USERNAME` environment variable | Cloud SQL database user |
| `CLOUD_SQL_CONNECTION_NAME` environment variable | `PROJECT_ID:REGION:INSTANCE_ID` |
| `DB_PASSWORD` secret environment variable | Reference the Secret Manager secret containing the database password |

Cloud Run supplies `PORT` automatically; do not set it manually. The service exposes health at `/actuator/health`.

## Before pushing to GitHub

`application-local.properties` is intentionally ignored because it can contain local database credentials. Do not commit passwords, service-account keys, or `.env` files.

For the first production deployment, `spring.jpa.hibernate.ddl-auto=update` can create the initial tables. Replace it with database migrations (for example Flyway) before managing schema changes in production.
