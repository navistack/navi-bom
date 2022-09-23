package org.navistack.framework.objectstorage.namebuilders;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.navistack.framework.http.MediaTypes;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class DateBasedFilenameBuilderTest {

    @Test
    void buildWithFilename() {
        SecureRandom random = Mockito.mock(SecureRandom.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(732);

        Clock clock = Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC"));

        DateBasedFilenameBuilder builder = new DateBasedFilenameBuilder();
        builder.setRandom(random);
        builder.setClock(clock);

        String filename = builder.build("TwoHardThings.txt", MediaTypes.TEXT_PLAIN.getFullType());
        assertThat(filename).isEqualTo("2011-12-03/20111203101530832_TwoHardThings.txt");
    }
}
