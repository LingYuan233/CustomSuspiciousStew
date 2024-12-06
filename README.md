# Custom Suspicious Stew

you can add your custom suspicious stew by edit config file.

你可以编辑配置文件来添加自定义的炖菜。

---

if you have version isolation, you should find the following path:

如果你开启了版本隔离，那么你应该寻找以下路径：

```.minecraft/versions/\<your version\>/config/customsuspiciousstew.json```

---

if you don't have version isolation, you should find the following path:

如果你没有开启版本隔离，那么你应该寻找以下路径：

```.minecraft/config/customsuspiciousstew.json```

---


the config file format is like this:

配置文件格式如下：

```json
[
  {
    "raw": "minecraft:diamond_block",
    "effectId": "minecraft:jump_boost",
    "duration": 10000
  }
]

```

as you can see, the config file is a json array, and each item in the array is a json object, which represents a custom stew.

如你所见，配置文件是一个json数组，数组中的每个元素都是json对象，而一个json对象指代一种自定义的炖菜。

you also can add more than one custom stew in the config file, like this:

你也可以在配置文件中添加多个自定义炖菜，如下所示：

```json
[
  {
    "raw": "minecraft:diamond_block",
    "effectId": "minecraft:jump_boost",
    "duration": 10000
  },
  {
    "raw": "minecraft:gold_block",
    "effectId": "minecraft:speed",
    "duration": 10000
  }
]
```

every json object has three properties:

而每个json对象都有三个属性：

- raw: the input item for the custom stew, like various types of flowers in vanilla minecraft, you also can use other mod provided items.
- effectId: the effect name of the custom stew, like "minecraft:jump_boost" for jump boost, you also can use other mod provided effects.
- duration: the duration of the effect, in ticks (1 second = 20 ticks, 1 minute = 1200 ticks).


- raw: 自定义炖菜的输入物品，比如原版 Minecraft 中的各种花朵，你也可以使用其他模组提供的物品。
- effectId: 自定义炖菜的效果名称，比如 "minecraft:jump_boost" 代表跳跃提升，你也可以使用其他模组提供的效果。
- duration: 效果持续时间，以 ticks 为单位（1 秒 = 20 ticks，1 分钟 = 1200 ticks）。

---

