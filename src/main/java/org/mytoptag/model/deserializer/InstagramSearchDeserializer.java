/*
 * Copyright (c) 2018 Stanislav Myachenkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package org.mytoptag.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mytoptag.model.InstagramTag;
import org.mytoptag.model.dto.InstagramSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstagramSearchDeserializer extends StdDeserializer<InstagramSearch> {

  protected InstagramSearchDeserializer() {
    this(null);
  }

  protected InstagramSearchDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public InstagramSearch deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    final JsonNode node = parser.getCodec().readTree(parser);
    final List<InstagramTag> tags = parseTags(node);
    final List users = new ArrayList();
    return new InstagramSearch(users, tags);
  }

  private List<InstagramTag> parseTags(JsonNode node) {
    final List<InstagramTag> result = new ArrayList<>();
    for (Iterator<JsonNode> it = node.get("hashtags").elements(); it.hasNext();) {
      JsonNode tagNode = it.next().get("hashtag");
      String name = tagNode.get("name").asText();
      Long igId = tagNode.get("id").asLong();
      Long count = tagNode.get("media_count").asLong();
      result.add(new InstagramTag(name, count, igId));
    }
    return result;
  }
}
