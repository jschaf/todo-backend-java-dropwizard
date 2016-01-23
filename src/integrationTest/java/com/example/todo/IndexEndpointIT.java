package com.example.todo;

import com.example.todo.api.TodoEntry;
import com.example.todo.db.Database;
import com.example.todo.repositories.AllTodos;
import com.squarespace.jersey2.guice.BootstrapUtils;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class IndexEndpointIT {


//    private static final String TMP_FILE = createTempFile();
    private static final String TMP_FILE = "C:\\users\\joe\\todo-test-db.h2";

    // ResourceHelper looks in ${basedir}/src/test/resources, but not integrationTest/resources so
    // the config file is under test/resources.
    // See http://stackoverflow.com/questions/29647284/dropwizard-integration-test-config-resource-file-not-found
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("integration-test-config.yml");

    @ClassRule
    public static final DropwizardAppRule<TodoConfiguration> RULE = new DropwizardAppRule<>(
            TodoApplication.class,
            CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));


    private final String SITE_URL = String.format("http://localhost:%d/", RULE.getLocalPort());

    private Client client;

    private static AllTodos allTodos;

    @BeforeClass
    public void migrateDb() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
        Database db = new Database();
        allTodos = new AllTodos(null);
    }

    @After
    public void tearDown() throws Exception {
        client.close();
        BootstrapUtils.reset();
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void getOfIndexReturns200() {
        Response response = client.target(SITE_URL + "todo")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void postToIndexReturnsPostedJson() {
        TodoEntry todoEntry = new TodoEntry(37, "Number 37", false, 2);
        allTodos.printHashMap();


        Response response = client.target(SITE_URL + "todoEntry")
                .request()
                .post(Entity.json(todoEntry));

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(TodoEntry.class)).isEqualTo(todoEntry);
        assertThat(allTodos.findById(37)).isEqualTo(Optional.of(todoEntry));
    }

}
