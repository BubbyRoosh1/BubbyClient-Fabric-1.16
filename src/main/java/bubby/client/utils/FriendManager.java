package bubby.client.utils;

import java.util.ArrayList;

public class FriendManager
{
  private final ArrayList<String> friends;

  public FriendManager()
  {
    friends = new ArrayList<String>();
    this.friends.add("BubbyRoosh");
    this.friends.add("Boiler3");
    this.friends.add("KarioMart");
  }

  public ArrayList<String>
  getFriends()
  {
    return this.friends;
  }

  public boolean
  isFriend(String name)
  {
    return this.friends.contains(name);
  }

  public void
  addFriend(String name)
  {
    if(this.friends.contains(name))
      return;
    this.friends.add(name);
    FileManager.INSTANCE.saveFriends();
  }

  public void
  addFriendNoSave(String name)
  {
    if(this.friends.contains(name))
      return;
    this.friends.add(name);
  }

  public void
  removeFriend(String name)
  {
    this.friends.remove(name);
    FileManager.INSTANCE.saveFriends();
  }
}
