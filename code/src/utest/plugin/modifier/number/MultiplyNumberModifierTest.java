/*
 * Copyright 2014 (C) Tom Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package plugin.modifier.number;

import pcgen.base.calculation.BasicCalculation;
import pcgen.base.format.NumberManager;
import pcgen.base.formula.base.LegalScope;
import pcgen.base.formula.base.ManagerFactory;
import pcgen.base.formula.inst.SimpleLegalScope;
import pcgen.base.solver.Modifier;
import pcgen.base.util.FormatManager;
import pcgen.rules.persistence.token.ModifierFactory;

import org.junit.Test;
import plugin.modifier.testsupport.EvalManagerUtilities;
import static org.junit.Assert.*;

public class MultiplyNumberModifierTest
{

	private LegalScope varScope = new SimpleLegalScope(null, "Global");
	private final FormatManager<Number> numManager = new NumberManager();

	@Test
	public void testInvalidConstruction()
	{
		try
		{
			ModifierFactory m = new MultiplyModifierFactory();
			m.getModifier(100, null, new ManagerFactory(){}, null, null, null);
			fail("Expected MultiplyModifier with null multiply value to fail");
		}
		catch (IllegalArgumentException | NullPointerException e)
		{
			//Yep!
		}
	}

	@Test
	public void testProcessNegative1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(6, modifier.process(-2, -3));
	}

	@Test
	public void testProcessNegative2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(8, modifier.process(-4, -2));
	}

	@Test
	public void testProcessPositive1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(6, modifier.process(2, 3));
	}

	@Test
	public void testProcessPositive2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(12, modifier.process(4, 3));
	}

	@Test
	public void testProcessZero1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0, modifier.process(0, 3));
	}

	@Test
	public void testProcessZero2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0, modifier.process(4, 0));
	}

	@Test
	public void testProcessZero3()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0, modifier.process(0, -3));
	}

	@Test
	public void testProcessZero4()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0, modifier.process(-4,0));
	}

	@Test
	public void testProcessMixed1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-35, modifier.process(5,-7));
	}

	@Test
	public void testProcessMixed2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-12, modifier.process(-4,3));
	}

	@Test
	public void testProcessDoubleNegative1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(3.57, modifier.process(-2.1, -1.7));
	}

	@Test
	public void testProcessDoubleNegative2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(4.16, modifier.process(-2.6, -1.6));
	}

	@Test
	public void testProcessDoublePositive1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(9.1, modifier.process(2.6, 3.5));
	}

	@Test
	public void testProcessDoublePositive2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(5.89, modifier.process(1.9, 3.1));
	}

	@Test
	public void testProcessDoubleZero1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0.0, modifier.process(0.0, 3.1));
	}

	@Test
	public void testProcessDoubleZero2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(0.0, modifier.process(4.2, 0.0));
	}

	@Test
	public void testProcessDoubleZero3()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-0.0, modifier.process(0.0, -3.4));
	}

	@Test
	public void testProcessDoubleZero4()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-0.0, modifier.process(-4.3,0.0));
	}

	@Test
	public void testProcessDoubleMixed1()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-38.16, modifier.process(5.3,-7.2));
	}

	@Test
	public void testProcessDoubleMixed2()
	{
		BasicCalculation modifier = new MultiplyModifierFactory();
		assertEquals(-3.08, modifier.process(-2.2,1.4));
	}

	@Test
	public void testGetModifier()
	{
		MultiplyModifierFactory factory = new MultiplyModifierFactory();
		Modifier<Number> modifier =
				factory.getModifier(35, "6.5", new ManagerFactory(){}, null, varScope, numManager);
		assertEquals((35l<<32)+factory.getInherentPriority(), modifier.getPriority());
		assertEquals(Number.class, modifier.getVariableFormat());
		assertEquals(27.95, modifier.process(EvalManagerUtilities.getInputEM(4.3)));
	}
}
