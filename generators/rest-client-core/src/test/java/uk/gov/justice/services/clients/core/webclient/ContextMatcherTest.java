package uk.gov.justice.services.clients.core.webclient;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.services.clients.core.EndpointDefinition;
import uk.gov.justice.services.common.configuration.JndiBasedServiceContextNameProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ContextMatcherTest {

    @Mock
    private JndiBasedServiceContextNameProvider contextNameProvider;

    @InjectMocks
    private ContextMatcher contextMatcher;

    @Test
    public void shouldReturnTrueIfTheCurrentServiceAndTheRemoteServiceAreTheSameContext() throws Exception {

        final String localServiceContextName = "notification-command-api";
        final String baseUri = "http://localhost:8080/notification-command-api/command/api/rest/notification";

        final EndpointDefinition endpointDefinition = mock(EndpointDefinition.class);

        when(contextNameProvider.getServiceContextName()).thenReturn(localServiceContextName);
        when(endpointDefinition.getBaseUri()).thenReturn(baseUri);

        assertThat(contextMatcher.isSameContext(endpointDefinition), is(true));
    }

    @Test
    public void shouldReturnFalseIfTheCurrentServiceAndTheRemoteServiceAreNotTheSameContext() throws Exception {

        final String localServiceContextName = "usersgroups-command-api";
        final String baseUri = "http://localhost:8080/notification-command-api/command/api/rest/notification";

        final EndpointDefinition endpointDefinition = mock(EndpointDefinition.class);

        when(contextNameProvider.getServiceContextName()).thenReturn(localServiceContextName);
        when(endpointDefinition.getBaseUri()).thenReturn(baseUri);

        assertThat(contextMatcher.isSameContext(endpointDefinition), is(false));
    }
}
