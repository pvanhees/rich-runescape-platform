package org.rrp.highscore.test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.rrp.highscore.HSParser
import org.scalatest.Tag

@RunWith(classOf[JUnitRunner])
class HSParserTest extends FlatSpec {
  
  val skills = List(
    "Overall"
	,"Attack"
	,"Defence"
	,"Strength"
	,"Constitution"
	,"Ranged"
	,"Prayer"
	,"Magic"
	,"Cooking"
	,"Woodcutting"
	,"Fletching"
	,"Fishing"
	,"Firemaking"
	,"Crafting"
	,"Smithing"
	,"Mining"
	,"Herblore"
	,"Agility"
	,"Thieving"
	,"Slayer"
	,"Farming"
	,"Runecrafting"
	,"Hunter"
	,"Construction"
	,"Summoning"
	,"Dungeoneering"
  ) 
  
  val activities = List(
	"Duel Tournament"
	,"Bounty Hunters"
	,"Bounty Hunter Rogues"
	,"Fist of Guthix"
	,"Mobilising Armies"
	,"B.A Attackers"
	,"B.A Defenders"
	,"B.A Collectors"
	,"B.A Healers"
	,"Castle Wars Games"
	,"Conquest"
	,"Dominion Tower"
	,"The Crucible"
  )  
  
  "HSParser" should "return all RS skills" taggedAs(Tag("IO")) in {
    expect(skills) {
	  HSParser.skills
    }
  }
  
  it should "return all RS activities" taggedAs(Tag("IO")) in {
    expect(activities) {
	  HSParser.activities
    }
  }

  it should "throw IllegalArgumentException" in {
    intercept[IllegalArgumentException] {
      HSParser.stats("324;a")
    }
  }
  
  it should "return all stats (rank, exp, level)" taggedAs(Tag("IO")) in {
    expect(99) {
      HSParser.stats("zezima")("Constitution")._2
    }
  }

}