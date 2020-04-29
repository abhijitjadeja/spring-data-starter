package starter;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public abstract class AbstractPersistable<T> implements Persistable<T>{

    @Transient
    private Operation crudOperation = Operation.READ;

    public void setNew(){
        this.crudOperation=Operation.CREATE;
    }

    @Override
    public boolean isNew() {
        return crudOperation == Operation.CREATE;
    }

    
}