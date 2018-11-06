package com.tz.config;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.sql.DataSourceDefinition;
import java.io.Serializable;


//@EnableMongoRepositories

@Data
@Document(collection = "model_metadata")
public class Metadata  implements Serializable {

    private static final long serialVersionUID = 1L;
//    private  Long id;
    @Id
    @Field("_id")
    private  String  _id ;

    @Field("")
    private  String MODELDB_model_id ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMODELDB_model_id() {
        return MODELDB_model_id;
    }

    public void setMODELDB_model_id(String MODELDB_model_id) {
        this.MODELDB_model_id = MODELDB_model_id;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "_id='" + _id + '\'' +
                ", MODELDB_model_id='" + MODELDB_model_id + '\'' +
                '}';
    }
}
