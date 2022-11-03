# typescript

## Basic

[TypeScript: Handbook - The TypeScript Handbook (typescriptlang.org)](https://www.typescriptlang.org/docs/handbook/intro.html)

* ECMA Script：[ECMAScript® 2023 Language Specification (tc39.es)](https://tc39.es/ecma262/)

* a static typechecker for JavaScript programs:
  * static: ts runs before your code runs.

* >In the interests of clarity and brevity, the main content of the Handbook will not explore every edge cases or minutiae of the feature being covered.

* terminology

* >what sorts of behaviors and capabilities it has.

* >That's part of what that `TypeError` is alluding to.

* >That means TypeScript can be leveraged for editing code too.

### Emitting with Errors

* >For example, imagine yourself migrating JavaScript code over to TypeScript and introducing type-checking errors.

* `noEmitOnError` compiler option:

  ```bash
  tsc --noEmitOnError hello.ts
  ```

### Explicit Types

* type annotations

  >You can read that signature as ”`greet` takes a `person` of type `string`, and a `date` of type `Date`“.

* backticks: \`
* concatenate, concatenations

### Downleveling

>This process of moving from a newer or “higher” version of ECMAScript down to an older or “lower” one is sometimes called *downleveling*.

* By default TypeScript targets ES3

### Strictness

* >This is the default experience with TypeScript, where types are optional, inference takes the most lenient types, and there's no checking for potentially `null`/`undefined` values.

* >This can require a little extra work, but generally speaking it pays for itself in the long run, and enables more thorough checks and more accurate tooling.

## Everyday Types

* >This isn't an exhaustive list , ...

* generics：泛型

[TypeScript: Documentation - Everyday Types (typescriptlang.org)](https://www.typescriptlang.org/docs/handbook/2/everyday-types.html)
