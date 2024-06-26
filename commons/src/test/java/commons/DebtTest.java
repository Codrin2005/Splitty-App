/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DebtTest {

	@Test
	public void checkConstructor() {
		var p = new Debt(new Expense(), new Participant(), 0);
		assertEquals(0, p.getAmount());
	}

	@Test
	public void checkEqualsMethod() {
		Debt d1 = new Debt(new Expense(), new Participant(), 0);
		Debt d2 = new Debt(new Expense(), new Participant(), 0);
		Debt d3 = new Debt(new Expense(), new Participant(), 1);
        assertEquals(d1, d2);
        assertNotEquals(d1, d3);
	}

	@Test
	public void checkHashMethod() {
		Debt d1 = new Debt(new Expense(), new Participant(), 0);
		Debt d2 = new Debt(new Expense(), new Participant(), 1);
		assertNotEquals(d1.hashCode(), d2.hashCode());
	}
}
