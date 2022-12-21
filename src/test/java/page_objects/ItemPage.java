package page_objects;

import utilities.wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 20.12.2022
 */
public class ItemPage {
    private static final String ITEM_NAME = "//*[@id='largeiteminfo_item_name']";
    private static final String ITEM_GAME_NAME = "//*[@id='largeiteminfo_game_name']";
    private static final String ITEM_RARITY = "//*[@id='largeiteminfo_item_type']";
    private static final String ITEM_HERO = "//*[@id='largeiteminfo_item_descriptors']//*[contains(text(),'Lifestealer')]";

    public ItemPage() { }

    public boolean compareItems(String itemName, String gameName, String heroName, String rarity) {
        boolean result = false;

        if(WaitUtil.setPresenceWait(ITEM_NAME).getText().equals(itemName) &&
                WaitUtil.setPresenceWait(ITEM_GAME_NAME).getText().equals(gameName) &&
                WaitUtil.setPresenceWait(ITEM_RARITY).getText().contains(rarity) &&
                WaitUtil.setPresenceWait(ITEM_HERO).getText().contains(heroName)) {
            result = true;
        }

        return result;
    }
}
