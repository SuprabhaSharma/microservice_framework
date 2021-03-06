package uk.gov.justice.services.eventsourcing.source.core;

import static uk.gov.justice.services.messaging.JsonEnvelope.envelopeFrom;
import static uk.gov.justice.services.messaging.JsonEnvelope.metadataFrom;

import uk.gov.justice.services.eventsourcing.source.core.exception.EventStreamException;
import uk.gov.justice.services.messaging.JsonEnvelope;

import java.util.UUID;

public interface EventAppender {

    /**
     * Stores the event in the event store.
     *
     * @param event    - the event to be appended
     * @param streamId - id of the stream the event will be part of
     * @param version  - version id of the event in the stream
     */
    void append(final JsonEnvelope event, final UUID streamId, final long version) throws EventStreamException;

    default JsonEnvelope eventFrom(final JsonEnvelope event, final UUID streamId, final long version) {
        return envelopeFrom(metadataFrom(event.metadata()).withStreamId(streamId).withVersion(version), event.payloadAsJsonObject());
    }
}
