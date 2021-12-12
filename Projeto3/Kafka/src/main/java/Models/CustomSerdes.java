package Models;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    static public final class MapperSerde
            extends Serdes.WrapperSerde<Mapper> {
        public MapperSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(Mapper.class));
        }
    }


    public static Serde<Mapper> Mapper() {
        return new CustomSerdes.MapperSerde();
    }
}
