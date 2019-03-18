package annotation;


/**
 * Book会被认为使用了@Authors({@Author(name="Raoul"), @Author(name =”Mario”), @Author(name=”Alan”)})
 */
@Author(name = "Raoul")
@Author(name = "Mario")
@Author(name = "Alan")
public interface Book {

    String name();

}
