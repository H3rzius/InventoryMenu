package me.filoghost.chestcommands.variable;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

import me.filoghost.chestcommands.util.collection.CollectionUtils;

public class RelativeStringList {

	private final ImmutableList<String> originalList;
	private final List<RelativeString> relativeList;
	private final boolean hasVariables;
	
	public RelativeStringList(List<String> list) {
		if (list != null) {
			this.originalList = ImmutableList.copyOf(list);
			this.relativeList = CollectionUtils.transform(list, RelativeString::of);
			this.hasVariables = this.relativeList.stream().anyMatch(RelativeString::hasVariables);
		} else {
			this.originalList = null;
			this.relativeList = null;
			this.hasVariables = false;
		}
	}
	
	public ImmutableList<String> getRawValue() {
		return originalList;
	}
	
	public List<String> getValue(Player player) {
		if (hasVariables) {
			return CollectionUtils.transform(relativeList, element -> element.getValue(player));
		} else {
			return originalList;
		}
	}
	
	public boolean hasVariables() {
		return hasVariables;
	}

}
