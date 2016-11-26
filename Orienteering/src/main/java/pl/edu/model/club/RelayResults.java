package pl.edu.model.club;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Bartosz on 2016-11-26.
 */
public class RelayResults {
    @Getter
    @Setter
    private Club relay;

    @Getter
    @Setter
    private long overallTime;

    public RelayResults(Club relay){
        this.relay = relay;
    }

}
