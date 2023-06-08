import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MainApp());
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {
  static const MethodChannel kotlin_functions =
      MethodChannel('my_kotlin_functions');

  String result = "";

  @override
  void initState() {
    super.initState();

    () async {
      result = await kotlin_functions
          .invokeMethod('get_a_string', <String, dynamic>{});

      //debug in here
      setState(() {});
    }();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: SingleChildScrollView(child: Text(result)),
        ),
      ),
    );
  }
}
