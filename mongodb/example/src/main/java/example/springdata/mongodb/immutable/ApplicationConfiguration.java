/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.mongodb.immutable;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

import com.mongodb.MongoClient;

/**
 * Test configuration to connect to a MongoDB named "test" and using a {@link MongoClient}. Also enables Spring Data
 * repositories for MongoDB.
 *
 * @author Mark Paluch
 */
@SpringBootApplication
class ApplicationConfiguration {

	/**
	 * Callback to update {@link ImmutablePerson}.
	 *
	 * @return a {@link BeforeConvertCallback} for {@link ImmutablePerson}.
	 */
	@Bean
	BeforeConvertCallback<ImmutablePerson> beforeConvertCallback() {

		return (immutablePerson, collection) -> {

			int randomNumber = ThreadLocalRandom.current().nextInt(1, 100);

			return immutablePerson.withRandomNumber(randomNumber);
		};
	}

}
