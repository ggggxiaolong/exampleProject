package com.mrtan.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test public void addition_isCorrect() throws Exception {
    assertEquals(4, 2 + 2);
  }

  @Test public void testError() {
    Observable.just(1).map(i -> {
      throw new RuntimeException("mine");
    }).subscribe(i -> assertEquals(2, i), e -> {
      assertEquals("mine", e.getMessage());
    });
  }

  @Test public void testRetry() {
    final AtomicInteger time = new AtomicInteger(0);
    Observable.just(1).map(i -> {
      time.getAndIncrement();
      throw new RuntimeException("mine");
    }).retry(1).subscribe(i -> {
    }, e -> { System.out.print(e.getMessage());
    });
    assertEquals(2, time.get());
  }

  @Test public void testGson() {
    final String json1 = "{\"success\":true}";
    final String json2 = "{\"success\":1}";
    Gson gson = new GsonBuilder().registerTypeAdapter(Success.class, new MyTA()).create();
    Success success = gson.fromJson(json1, Success.class);
    Success success2 = gson.fromJson(json2, Success.class);
    assertTrue(success.success);
    assertTrue(success2.success);
  }

  @Test public void testSubString(){
    String ori = "1231243.42";
    String res = ".42";
    assertEquals(ori.substring(ori.lastIndexOf(".")), res);
  }

  static class Success {
    public boolean success;
  }

  static class MyTA extends TypeAdapter<Success> {

    @Override public void write(JsonWriter out, Success value) throws IOException {
      out.beginObject();
      out.name("success");
      out.value(value.success);
      out.endObject();
    }

    @Override public Success read(JsonReader in) throws IOException {
      in.beginObject();
      String name = in.nextName();
      boolean value = false;
      if (name.equals("success")) {
        JsonToken peek = in.peek();
        if (peek == JsonToken.BOOLEAN) {
          value = in.nextBoolean();
        } else {
          value = in.nextInt() != 0;
        }
      }
      in.endObject();
      Success success = new Success();
      success.success = value;
      return success;
    }
  }
}