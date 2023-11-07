CREATE TABLE reservation(
        reservation_ID              int             NOT NULL        AUTO_INCREMENT,
        user_ID_FK                  int             NOT NULL,
        event                       int             NOT NULL,
        reservation_created_date    timestamp       NOT NULL,
        reservation_date            timestamp       NOT NULL,
        start_time                  time            NOT NULL,
        end_time                    time            NOT NULL,
        PRIMARY KEY (reservation_ID),
        UNIQUE (reservation_date)
)