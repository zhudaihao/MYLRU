# MYLRU
磁盘缓存封装类
DiskLruCache目前磁盘缓存比较好的方案，但是由于其的存取API，并不是特别好用。


实现对象存储
//存对象
User user = new User("李四");
helper.putObject("user", user);


//取对象
 //注意bean对象需要有个空参数的构造方法
 User user = (User) helper.getAsObjects("user");
 Toast.makeText(this, "" + user.getName(), Toast.LENGTH_SHORT).show();
 
 
 本库是基于Jack大神 鸿洋基础上封装 面向对象的操作，如有不当地方请告知改正

